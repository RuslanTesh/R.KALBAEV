package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.dao.RatingDao;
import academy.tochkavhoda.competition.daoimpl.ApplicationDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ExpertDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ParticipantDaoImpl;
import academy.tochkavhoda.competition.daoimpl.RatingDaoImpl;
import academy.tochkavhoda.competition.dto.request.AddApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.ApplicationIdDtoRequest;
import academy.tochkavhoda.competition.dto.request.DirectionsDtoRequest;
import academy.tochkavhoda.competition.dto.response.AddApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationsDtoResponse;
import academy.tochkavhoda.competition.dto.response.EmptyDtoResponse;
import academy.tochkavhoda.competition.dto.response.ParticipantDtoResponse;
import academy.tochkavhoda.competition.dto.response.ParticipantsDtoResponse;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationService {
    private final AuthService auth = new AuthService();
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final ParticipantDao participantDao = new ParticipantDaoImpl();
    private final ExpertDao expertDao = new ExpertDaoImpl();
    private final RatingDao ratingDao = new RatingDaoImpl();

    public AddApplicationDtoResponse add(String token, AddApplicationDtoRequest r) throws ServiceException {
        Participant p = auth.participant(token);
        auth.checkOpen();
        String title = Validator.notBlank(r.title(), "title");
        String description = Validator.notBlank(r.description(), "description");
        Set<String> directions = Validator.directions(r.directions());
        if (r.amount() <= 0) {
            throw new ServiceException("Amount must be positive");
        }
        int id = applicationDao.insert(title, description, directions, r.amount(), p.login());
        return new AddApplicationDtoResponse(id);
    }

    public EmptyDtoResponse cancel(String token, ApplicationIdDtoRequest r) throws ServiceException {
        Participant p = auth.participant(token);
        auth.checkOpen();
        Application a = applicationDao.get(r.applicationId());
        if (a == null || !a.ownerLogin().equals(p.login())) {
            throw new ServiceException("Application not found");
        }
        ratingDao.removeByApplication(a.id());
        applicationDao.delete(a.id());
        return new EmptyDtoResponse();
    }

    /**
     * Expert: applications in his directions (optionally narrowed by the requested ones), with his ratings.
     * Participant: his own applications (optionally filtered by directions).
     */
    public ApplicationsDtoResponse getApplications(String token, DirectionsDtoRequest r) throws ServiceException {
        String login = auth.loginOf(token);
        Set<String> filter = Validator.optionalDirections(r == null ? null : r.directions());
        Expert expert = expertDao.get(login);
        Set<String> wanted;
        if (expert != null) {
            wanted = new HashSet<>(expert.directions());
            if (!filter.isEmpty()) {
                wanted.retainAll(filter);
            }
        } else {
            wanted = filter;
        }
        Map<Integer, Integer> myRatings = expert != null ? ratingDao.getByExpert(login) : Map.of();
        List<ApplicationDtoResponse> result = new ArrayList<>();
        for (Application a : applicationDao.getAll()) {
            if (expert == null && !a.ownerLogin().equals(login)) {
                continue;
            }
            if ((expert != null || !filter.isEmpty()) && Collections.disjoint(a.directions(), wanted)) {
                continue;
            }
            result.add(toDto(a, myRatings.get(a.id())));
        }
        return new ApplicationsDtoResponse(result);
    }

    public ParticipantsDtoResponse getParticipants(String token, DirectionsDtoRequest r) throws ServiceException {
        auth.loginOf(token);
        Set<String> filter = Validator.optionalDirections(r == null ? null : r.directions());
        Set<String> logins = new HashSet<>();
        for (Application a : applicationDao.getAll()) {
            if (filter.isEmpty() || !Collections.disjoint(a.directions(), filter)) {
                logins.add(a.ownerLogin());
            }
        }
        List<ParticipantDtoResponse> result = new ArrayList<>();
        for (Participant p : participantDao.getAll()) {
            if (filter.isEmpty() || logins.contains(p.login())) {
                result.add(new ParticipantDtoResponse(p.firstName(), p.lastName(), p.firmName(), p.login()));
            }
        }
        return new ParticipantsDtoResponse(result);
    }

    static ApplicationDtoResponse toDto(Application a, Integer rating) {
        return new ApplicationDtoResponse(a.id(), a.title(), a.description(), new ArrayList<>(a.directions()),
                a.amount(), a.ownerLogin(), rating);
    }
}
