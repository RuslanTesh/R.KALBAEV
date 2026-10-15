package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.CompetitionDao;
import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.dao.SessionDao;
import academy.tochkavhoda.competition.daoimpl.CompetitionDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ExpertDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ParticipantDaoImpl;
import academy.tochkavhoda.competition.daoimpl.SessionDaoImpl;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;

/** Token and role checks shared by all services. */
public class AuthService {
    private final SessionDao sessionDao = new SessionDaoImpl();
    private final ParticipantDao participantDao = new ParticipantDaoImpl();
    private final ExpertDao expertDao = new ExpertDaoImpl();
    private final CompetitionDao competitionDao = new CompetitionDaoImpl();

    public String loginOf(String token) throws ServiceException {
        String login = sessionDao.getLogin(token);
        if (login == null) {
            throw new ServiceException("Invalid token");
        }
        return login;
    }

    public Participant participant(String token) throws ServiceException {
        Participant p = participantDao.get(loginOf(token));
        if (p == null) {
            throw new ServiceException("Only participants can perform this operation");
        }
        return p;
    }

    public Expert expert(String token) throws ServiceException {
        Expert e = expertDao.get(loginOf(token));
        if (e == null) {
            throw new ServiceException("Only experts can perform this operation");
        }
        return e;
    }

    public void checkOpen() throws ServiceException {
        if (competitionDao.isFinished()) {
            throw new ServiceException("Competition is finished");
        }
    }
}
