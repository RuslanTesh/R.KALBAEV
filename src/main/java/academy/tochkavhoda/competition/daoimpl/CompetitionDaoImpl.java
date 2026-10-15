package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.CompetitionDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Grant;

import java.util.List;

public class CompetitionDaoImpl implements CompetitionDao {
    private final Database db = Database.getInstance();

    @Override
    public boolean isFinished() {
        return db.isFinished();
    }

    @Override
    public void finish(List<Grant> grants, long remaining) {
        db.finish(grants, remaining);
    }

    @Override
    public List<Grant> getGrants() {
        return db.getGrants();
    }

    @Override
    public long getRemaining() {
        return db.getRemaining();
    }
}
