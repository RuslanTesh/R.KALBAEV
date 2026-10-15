package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.dao.RatingDao;
import academy.tochkavhoda.competition.dao.SessionDao;
import academy.tochkavhoda.competition.daoimpl.ApplicationDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ExpertDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ParticipantDaoImpl;
import academy.tochkavhoda.competition.daoimpl.RatingDaoImpl;
import academy.tochkavhoda.competition.daoimpl.SessionDaoImpl;
import academy.tochkavhoda.competition.dto.request.LoginDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterExpertDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterParticipantDtoRequest;
import academy.tochkavhoda.competition.dto.response.EmptyDtoResponse;
import academy.tochkavhoda.competition.dto.response.TokenDtoResponse;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.Set;

public class UserService {
    private final AuthService auth = new AuthService();
    private final ParticipantDao participantDao = new ParticipantDaoImpl();
    private final ExpertDao expertDao = new ExpertDaoImpl();
    private final SessionDao sessionDao = new SessionDaoImpl();
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final RatingDao ratingDao = new RatingDaoImpl();

    public TokenDtoResponse registerParticipant(RegisterParticipantDtoRequest r) throws ServiceException {
        auth.checkOpen();
        String firstName = Validator.notBlank(r.firstName(), "firstName");
        String lastName = Validator.notBlank(r.lastName(), "lastName");
        String firmName = Validator.notBlank(r.firmName(), "firmName");
        String login = Validator.login(r.login());
        String password = Validator.password(r.password());
        checkLoginFree(login);
        participantDao.insert(new Participant(firstName, lastName, firmName, login, password));
        return new TokenDtoResponse(sessionDao.create(login));
    }

    public TokenDtoResponse registerExpert(RegisterExpertDtoRequest r) throws ServiceException {
        auth.checkOpen();
        String firstName = Validator.notBlank(r.firstName(), "firstName");
        String lastName = Validator.notBlank(r.lastName(), "lastName");
        Set<String> directions = Validator.directions(r.directions());
        String login = Validator.login(r.login());
        String password = Validator.password(r.password());
        checkLoginFree(login);
        expertDao.insert(new Expert(firstName, lastName, directions, login, password));
        return new TokenDtoResponse(sessionDao.create(login));
    }

    public TokenDtoResponse login(LoginDtoRequest r) throws ServiceException {
        if (r.login() == null || r.password() == null) {
            throw new ServiceException("Wrong login or password");
        }
        Participant p = participantDao.get(r.login().trim());
        if (p != null && p.password().equals(r.password())) {
            return new TokenDtoResponse(sessionDao.create(p.login()));
        }
        Expert e = expertDao.get(r.login().trim());
        if (e != null && e.password().equals(r.password())) {
            return new TokenDtoResponse(sessionDao.create(e.login()));
        }
        throw new ServiceException("Wrong login or password");
    }

    public EmptyDtoResponse logout(String token) throws ServiceException {
        auth.loginOf(token);
        sessionDao.remove(token);
        return new EmptyDtoResponse();
    }

    /** Removes the user with everything attached to him (applications, ratings, all tokens). */
    public EmptyDtoResponse leave(String token) throws ServiceException {
        String login = auth.loginOf(token);
        if (participantDao.get(login) != null) {
            for (Application a : new ArrayList<>(applicationDao.getAll())) {
                if (a.ownerLogin().equals(login)) {
                    ratingDao.removeByApplication(a.id());
                    applicationDao.delete(a.id());
                }
            }
            participantDao.delete(login);
        } else {
            ratingDao.removeByExpert(login);
            expertDao.delete(login);
        }
        sessionDao.removeByLogin(login);
        return new EmptyDtoResponse();
    }

    private void checkLoginFree(String login) throws ServiceException {
        if (participantDao.get(login) != null || expertDao.get(login) != null) {
            throw new ServiceException("Login is already used");
        }
    }
}
