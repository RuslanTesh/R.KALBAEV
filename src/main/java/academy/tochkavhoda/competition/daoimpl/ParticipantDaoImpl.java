package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Participant;

import java.util.Collection;

public class ParticipantDaoImpl implements ParticipantDao {
    private final Database db = Database.getInstance();

    @Override
    public void insert(Participant participant) {
        db.getParticipants().put(participant.login(), participant);
    }

    @Override
    public Participant get(String login) {
        return db.getParticipants().get(login);
    }

    @Override
    public void delete(String login) {
        db.getParticipants().remove(login);
    }

    @Override
    public Collection<Participant> getAll() {
        return db.getParticipants().values();
    }
}
