package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.dto.response.AddApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationsDtoResponse;
import academy.tochkavhoda.competition.dto.response.CompetitionResultDtoResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static academy.tochkavhoda.competition.server.TestServerUsers.assertError;
import static academy.tochkavhoda.competition.server.TestServerUsers.expert;
import static academy.tochkavhoda.competition.server.TestServerUsers.participant;
import static academy.tochkavhoda.competition.server.TestServerUsers.token;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestServerCompetition {
    private static final Gson GSON = new Gson();
    private Server server;
    private String firm1;
    private String firm2;
    private String math;
    private String art;

    @BeforeEach
    public void setUp() {
        server = new Server(new Random(1));
        server.clear();
        firm1 = token(server.registerParticipant(participant("firm_one1", "pass_w0rd!")));
        firm2 = token(server.registerParticipant(participant("firm_two2", "pass_w0rd!")));
        math = token(server.registerExpert(expert("math_expert", "pass_w0rd!", "\"Math\",\"IT\"")));
        art = token(server.registerExpert(expert("art_expert1", "pass_w0rd!", "\"art\"")));
    }

    private int add(String token, String title, String directions, long amount) {
        ServerResponse r = server.addApplication(token, "{\"title\":\"" + title + "\",\"description\":\"desc\","
                + "\"directions\":[" + directions + "],\"amount\":" + amount + "}");
        assertEquals(200, r.getResponseCode(), r.getResponseData());
        return GSON.fromJson(r.getResponseData(), AddApplicationDtoResponse.class).applicationId();
    }

    private ServerResponse rate(String token, int id, int rating) {
        return server.rateApplication(token, "{\"applicationId\":" + id + ",\"rating\":" + rating + "}");
    }

    private CompetitionResultDtoResponse finish(long fund, double threshold) {
        ServerResponse r = server.finishCompetition(math, "{\"fund\":" + fund + ",\"threshold\":" + threshold + "}");
        assertEquals(200, r.getResponseCode(), r.getResponseData());
        return GSON.fromJson(r.getResponseData(), CompetitionResultDtoResponse.class);
    }

    private List<Integer> grantIds(CompetitionResultDtoResponse r) {
        return r.grants().stream().map(g -> g.applicationId()).toList();
    }

    @Test
    public void testAddApplicationErrors() {
        assertError(server.addApplication(math, "{\"title\":\"t\",\"description\":\"d\",\"directions\":[\"math\"],"
                + "\"amount\":5}"));
        assertError(server.addApplication("bad", "{}"));
        assertError(server.addApplication(firm1, "{\"title\":\"\",\"description\":\"d\",\"directions\":[\"math\"],"
                + "\"amount\":5}"));
        assertError(server.addApplication(firm1, "{\"title\":\"t\",\"description\":\"d\",\"directions\":[],"
                + "\"amount\":5}"));
        assertError(server.addApplication(firm1, "{\"title\":\"t\",\"description\":\"d\",\"directions\":[\"math\"],"
                + "\"amount\":0}"));
    }

    @Test
    public void testCancelApplication() {
        int id = add(firm1, "A", "\"math\"", 100);
        assertError(server.cancelApplication(firm2, "{\"applicationId\":" + id + "}"));
        assertError(server.cancelApplication(math, "{\"applicationId\":" + id + "}"));
        assertEquals(200, server.cancelApplication(firm1, "{\"applicationId\":" + id + "}").getResponseCode());
        assertError(server.cancelApplication(firm1, "{\"applicationId\":" + id + "}"));
        assertError(rate(math, id, 5));
    }

    @Test
    public void testExpertSeesOnlyOwnDirections() {
        int m = add(firm1, "Math app", "\"math\"", 100);
        add(firm1, "Art app", "\"art\"", 100);
        ApplicationsDtoResponse r = GSON.fromJson(server.getApplications(math, "{}").getResponseData(),
                ApplicationsDtoResponse.class);
        assertEquals(1, r.applications().size());
        assertEquals(m, r.applications().get(0).id());

        r = GSON.fromJson(server.getApplications(math, "{\"directions\":[\"art\"]}").getResponseData(),
                ApplicationsDtoResponse.class);
        assertEquals(0, r.applications().size());

        r = GSON.fromJson(server.getApplications(firm2, "{}").getResponseData(), ApplicationsDtoResponse.class);
        assertEquals(0, r.applications().size());
    }

    @Test
    public void testRateChangeRemoveAndMyRatings() {
        int id = add(firm1, "A", "\"math\"", 100);
        assertEquals(200, rate(math, id, 3).getResponseCode());
        assertEquals(200, rate(math, id, 5).getResponseCode());
        ApplicationsDtoResponse mine = GSON.fromJson(server.getMyRatings(math).getResponseData(),
                ApplicationsDtoResponse.class);
        assertEquals(1, mine.applications().size());
        assertEquals(5, mine.applications().get(0).rating());

        assertEquals(200, server.removeRating(math, "{\"applicationId\":" + id + "}").getResponseCode());
        assertError(server.removeRating(math, "{\"applicationId\":" + id + "}"));
        mine = GSON.fromJson(server.getMyRatings(math).getResponseData(), ApplicationsDtoResponse.class);
        assertEquals(0, mine.applications().size());
    }

    @Test
    public void testRateErrors() {
        int id = add(firm1, "A", "\"math\"", 100);
        assertError(rate(math, id, 0));
        assertError(rate(math, id, 6));
        assertError(rate(math, 999, 3));
        assertError(rate(art, id, 3));
        assertError(rate(firm1, id, 3));
        assertError(rate("bad", id, 3));
        assertError(server.getMyRatings(firm1));
    }

    @Test
    public void testExpertLeavesRatingsDisappear() {
        int id = add(firm1, "A", "\"math\"", 100);
        rate(math, id, 5);
        assertEquals(200, server.leaveServer(math).getResponseCode());
        String other = token(server.registerExpert(expert("other_exprt", "pass_w0rd!", "\"math\"")));
        rate(other, id, 2);
        CompetitionResultDtoResponse r = GSON.fromJson(server.finishCompetition(other,
                "{\"fund\":1000,\"threshold\":2.5}").getResponseData(), CompetitionResultDtoResponse.class);
        assertEquals(0, r.grants().size());
    }

    @Test
    public void testParticipantLeavesApplicationsDisappear() {
        int id = add(firm1, "A", "\"math\"", 100);
        rate(math, id, 5);
        server.leaveServer(firm1);
        assertError(rate(math, id, 4));
        assertEquals(0, finish(1000, 0).grants().size());
    }

    @Test
    public void testFinishRanking() {
        int a = add(firm1, "A", "\"math\"", 300);
        int b = add(firm1, "B", "\"math\"", 200);
        int c = add(firm2, "C", "\"it\"", 100);
        int d = add(firm2, "D", "\"math\"", 50);
        rate(math, a, 5);
        rate(math, b, 5);
        rate(math, c, 4);
        rate(math, d, 1);
        // fund 450: B (200) then A (300) does not fit -> skipped, C (100) fits; D below threshold 2
        CompetitionResultDtoResponse r = finish(450, 2);
        assertEquals(List.of(b, c), grantIds(r));
        assertEquals(150, r.remaining());
    }

    @Test
    public void testFinishEqualRatingsCheaperFirst() {
        int a = add(firm1, "A", "\"math\"", 300);
        int b = add(firm1, "B", "\"math\"", 100);
        rate(math, a, 4);
        rate(math, b, 4);
        CompetitionResultDtoResponse r = finish(350, 0);
        assertEquals(List.of(b), grantIds(r));
        assertEquals(250, r.remaining());
    }

    @Test
    public void testFinishAverageOfSeveralExperts() {
        String other = token(server.registerExpert(expert("other_exprt", "pass_w0rd!", "\"math\"")));
        int a = add(firm1, "A", "\"math\"", 100);
        int b = add(firm1, "B", "\"math\"", 100);
        rate(math, a, 5);
        rate(other, a, 3);   // avg 4
        rate(math, b, 4);
        rate(other, b, 5);   // avg 4.5
        CompetitionResultDtoResponse r = finish(100, 0);
        assertEquals(List.of(b), grantIds(r));
    }

    @Test
    public void testFinishThresholdAndUnrated() {
        int a = add(firm1, "A", "\"math\"", 10);
        add(firm1, "unrated", "\"math\"", 10);
        rate(math, a, 3);
        assertEquals(List.of(a), grantIds(finish(1000, 0)));
    }

    @Test
    public void testFinishClosesCompetition() {
        int a = add(firm1, "A", "\"math\"", 10);
        rate(math, a, 5);
        CompetitionResultDtoResponse first = finish(100, 0);
        assertError(rate(math, a, 1));
        assertError(server.removeRating(math, "{\"applicationId\":" + a + "}"));
        assertError(server.cancelApplication(firm1, "{\"applicationId\":" + a + "}"));
        assertError(server.addApplication(firm1, "{\"title\":\"t\",\"description\":\"d\",\"directions\":[\"math\"],"
                + "\"amount\":5}"));
        assertError(server.registerParticipant(participant("firm_new11", "pass_w0rd!")));
        // second call returns the stored result, parameters are ignored
        CompetitionResultDtoResponse second = finish(0, 4);
        assertEquals(first, second);
        assertTrue(!second.grants().isEmpty());
    }

    @Test
    public void testFinishWrongParams() {
        assertError(server.finishCompetition("bad", "{\"fund\":10,\"threshold\":1}"));
        assertError(server.finishCompetition(math, "{\"fund\":-1,\"threshold\":1}"));
        assertError(server.finishCompetition(math, "{\"fund\":10,\"threshold\":7}"));
        assertError(server.finishCompetition(math, "oops"));
    }
}
