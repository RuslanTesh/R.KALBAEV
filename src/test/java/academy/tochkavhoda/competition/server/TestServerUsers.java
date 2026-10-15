package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.dto.response.TokenDtoResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestServerUsers {
    static final Gson GSON = new Gson();
    Server server;

    @BeforeEach
    public void setUp() {
        server = new Server();
        server.clear();
    }

    static String participant(String login, String password) {
        return "{\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"firmName\":\"Firm\",\"login\":\"" + login
                + "\",\"password\":\"" + password + "\"}";
    }

    static String expert(String login, String password, String directions) {
        return "{\"firstName\":\"Anna\",\"lastName\":\"Sidorova\",\"directions\":[" + directions + "],\"login\":\""
                + login + "\",\"password\":\"" + password + "\"}";
    }

    static String token(ServerResponse r) {
        assertEquals(200, r.getResponseCode(), r.getResponseData());
        TokenDtoResponse t = GSON.fromJson(r.getResponseData(), TokenDtoResponse.class);
        assertNotNull(t.token());
        return t.token();
    }

    static void assertError(ServerResponse r) {
        assertEquals(400, r.getResponseCode());
        assertTrue(r.getResponseData().contains("\"error\""), r.getResponseData());
    }

    @Test
    public void testRegisterParticipant() {
        String token = token(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
        assertEquals(200, server.logout(token).getResponseCode());
    }

    @Test
    public void testRegisterExpert() {
        token(server.registerExpert(expert("expert_login", "pass_w0rd!", "\"math\",\"it\"")));
    }

    @Test
    public void testRegisterTwiceSameLogin() {
        token(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
        assertError(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
        assertError(server.registerExpert(expert("firm_login", "pass_w0rd!", "\"math\"")));
    }

    @Test
    public void testRegisterWrongData() {
        assertError(server.registerParticipant(participant("short", "pass_w0rd!")));
        assertError(server.registerParticipant(participant("firm_login", "short1!")));
        assertError(server.registerParticipant(participant("firm_login", "nodigits!!")));
        assertError(server.registerParticipant(participant("firm_login", "nospecial12")));
        assertError(server.registerParticipant(participant("   ", "pass_w0rd!")));
        assertError(server.registerParticipant("{\"login\":\"firm_login\"}"));
        assertError(server.registerParticipant("not a json"));
        assertError(server.registerParticipant(null));
        assertError(server.registerExpert(expert("expert_login", "pass_w0rd!", "")));
        assertError(server.registerExpert(expert("expert_login", "pass_w0rd!", "\" \"")));
    }

    @Test
    public void testLoginAfterLogout() {
        String t1 = token(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
        assertEquals(200, server.logout(t1).getResponseCode());
        assertError(server.logout(t1));
        String t2 = token(server.login("{\"login\":\"firm_login\",\"password\":\"pass_w0rd!\"}"));
        assertNotEquals(t1, t2);
        assertEquals(200, server.logout(t2).getResponseCode());
    }

    @Test
    public void testLoginExpert() {
        token(server.registerExpert(expert("expert_login", "pass_w0rd!", "\"math\"")));
        token(server.login("{\"login\":\"expert_login\",\"password\":\"pass_w0rd!\"}"));
    }

    @Test
    public void testLoginWrong() {
        token(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
        assertError(server.login("{\"login\":\"firm_login\",\"password\":\"wrong_pass1!\"}"));
        assertError(server.login("{\"login\":\"unknown_login\",\"password\":\"pass_w0rd!\"}"));
        assertError(server.login("{}"));
        assertError(server.login("garbage"));
    }

    @Test
    public void testLeaveServer() {
        String t = token(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
        assertEquals(200, server.leaveServer(t).getResponseCode());
        assertError(server.logout(t));
        assertError(server.login("{\"login\":\"firm_login\",\"password\":\"pass_w0rd!\"}"));
        // login can be registered again after leaving
        token(server.registerParticipant(participant("firm_login", "pass_w0rd!")));
    }

    @Test
    public void testInvalidTokenRejected() {
        assertError(server.logout("no-such-token"));
        assertError(server.leaveServer(null));
        assertError(server.getParticipants("no-such-token", "{}"));
    }

    @Test
    public void testGetParticipantsFilteredByDirection() {
        String p1 = token(server.registerParticipant(participant("firm_one1", "pass_w0rd!")));
        String p2 = token(server.registerParticipant(participant("firm_two2", "pass_w0rd!")));
        server.addApplication(p1, "{\"title\":\"A\",\"description\":\"d\",\"directions\":[\"math\"],\"amount\":10}");
        server.addApplication(p2, "{\"title\":\"B\",\"description\":\"d\",\"directions\":[\"art\"],\"amount\":10}");

        ServerResponse all = server.getParticipants(p1, "{}");
        assertEquals(200, all.getResponseCode());
        assertTrue(all.getResponseData().contains("firm_one1") && all.getResponseData().contains("firm_two2"));

        ServerResponse math = server.getParticipants(p1, "{\"directions\":[\"Math\"]}");
        assertTrue(math.getResponseData().contains("firm_one1"));
        assertTrue(!math.getResponseData().contains("firm_two2"));
    }
}
