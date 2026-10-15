package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Expert;

import java.util.Collection;

public class ExpertDaoImpl implements ExpertDao {
    private final Database db = Database.getInstance();

    @Override
    public void insert(Expert expert) {
        db.getExperts().put(expert.login(), expert);
    }

    @Override
    public Expert get(String login) {
        return db.getExperts().get(login);
    }

    @Override
    public void delete(String login) {
        db.getExperts().remove(login);
    }

    @Override
    public Collection<Expert> getAll() {
        return db.getExperts().values();
    }
}
