package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.dto.request.AddApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.ApplicationIdDtoRequest;
import academy.tochkavhoda.competition.dto.request.DirectionsDtoRequest;
import academy.tochkavhoda.competition.dto.request.FinishCompetitionDtoRequest;
import academy.tochkavhoda.competition.dto.request.LoginDtoRequest;
import academy.tochkavhoda.competition.dto.request.RateApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterExpertDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterParticipantDtoRequest;
import academy.tochkavhoda.competition.dto.response.ErrorDtoResponse;
import academy.tochkavhoda.competition.service.ApplicationService;
import academy.tochkavhoda.competition.service.CompetitionService;
import academy.tochkavhoda.competition.service.RatingService;
import academy.tochkavhoda.competition.service.ServiceException;
import academy.tochkavhoda.competition.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.util.Random;

/** Entry point of the grant competition server: every public method takes JSON and returns a ServerResponse. */
public class Server {
    private static final int OK = 200;
    private static final int ERROR = 400;

    private interface Action {
        Object run() throws ServiceException;
    }

    private final Gson gson = new Gson();
    private final UserService userService = new UserService();
    private final ApplicationService applicationService = new ApplicationService();
    private final RatingService ratingService = new RatingService();
    private final CompetitionService competitionService;

    public Server() {
        this(new Random());
    }

    /** The random generator is used to order applications with equal rating and amount. */
    public Server(Random random) {
        this.competitionService = new CompetitionService(random);
    }

    /** Drops all server data (new empty server state). */
    public void clear() {
        Database.getInstance().clear();
    }

    public ServerResponse registerParticipant(String requestJsonString) {
        return execute(() -> userService.registerParticipant(
                parse(requestJsonString, RegisterParticipantDtoRequest.class)));
    }

    public ServerResponse registerExpert(String requestJsonString) {
        return execute(() -> userService.registerExpert(parse(requestJsonString, RegisterExpertDtoRequest.class)));
    }

    public ServerResponse login(String requestJsonString) {
        return execute(() -> userService.login(parse(requestJsonString, LoginDtoRequest.class)));
    }

    public ServerResponse logout(String token) {
        return execute(() -> userService.logout(token));
    }

    /** The user leaves the server: all his data (applications, ratings) is deleted. */
    public ServerResponse leaveServer(String token) {
        return execute(() -> userService.leave(token));
    }

    public ServerResponse addApplication(String token, String requestJsonString) {
        return execute(() -> applicationService.add(token, parse(requestJsonString, AddApplicationDtoRequest.class)));
    }

    public ServerResponse cancelApplication(String token, String requestJsonString) {
        return execute(() -> applicationService.cancel(token, parse(requestJsonString, ApplicationIdDtoRequest.class)));
    }

    public ServerResponse getApplications(String token, String requestJsonString) {
        return execute(() -> applicationService.getApplications(token, parseOptional(requestJsonString)));
    }

    public ServerResponse getParticipants(String token, String requestJsonString) {
        return execute(() -> applicationService.getParticipants(token, parseOptional(requestJsonString)));
    }

    public ServerResponse rateApplication(String token, String requestJsonString) {
        return execute(() -> ratingService.rate(token, parse(requestJsonString, RateApplicationDtoRequest.class)));
    }

    public ServerResponse removeRating(String token, String requestJsonString) {
        return execute(() -> ratingService.remove(token, parse(requestJsonString, ApplicationIdDtoRequest.class)));
    }

    public ServerResponse getMyRatings(String token) {
        return execute(() -> ratingService.getMyRatings(token));
    }

    public ServerResponse finishCompetition(String token, String requestJsonString) {
        return execute(() -> competitionService.finish(token,
                parse(requestJsonString, FinishCompetitionDtoRequest.class)));
    }

    private ServerResponse execute(Action action) {
        try {
            return new ServerResponse(OK, gson.toJson(action.run()));
        } catch (ServiceException e) {
            return error(e.getMessage());
        } catch (RuntimeException e) {
            return error("Internal error");
        }
    }

    private ServerResponse error(String message) {
        return new ServerResponse(ERROR, gson.toJson(new ErrorDtoResponse(message)));
    }

    private <T> T parse(String json, Class<T> cls) throws ServiceException {
        try {
            T value = json == null ? null : gson.fromJson(json, cls);
            if (value == null) {
                throw new ServiceException("Wrong request");
            }
            return value;
        } catch (JsonParseException e) {
            throw new ServiceException("Wrong request");
        }
    }

    private DirectionsDtoRequest parseOptional(String json) throws ServiceException {
        if (json == null || json.isBlank()) {
            return new DirectionsDtoRequest(null);
        }
        return parse(json, DirectionsDtoRequest.class);
    }
}
