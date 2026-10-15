package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Application;

import java.util.Collection;
import java.util.Set;

public class ApplicationDaoImpl implements ApplicationDao {
    private final Database db = Database.getInstance();

    @Override
    public int insert(String title, String description, Set<String> directions, long amount, String owner) {
        int id = db.nextApplicationId();
        db.getApplications().put(id, new Application(id, title, description, directions, amount, owner));
        return id;
    }

    @Override
    public Application get(int id) {
        return db.getApplications().get(id);
    }

    @Override
    public void delete(int id) {
        db.getApplications().remove(id);
    }

    @Override
    public Collection<Application> getAll() {
        return db.getApplications().values();
    }
}
