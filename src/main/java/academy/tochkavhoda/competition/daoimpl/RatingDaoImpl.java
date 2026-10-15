package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.RatingDao;
import academy.tochkavhoda.competition.database.Database;

import java.util.HashMap;
import java.util.Map;

public class RatingDaoImpl implements RatingDao {
    private final Database db = Database.getInstance();

    @Override
    public void put(int applicationId, String expertLogin, int rating) {
        db.getRatings().computeIfAbsent(applicationId, k -> new HashMap<>()).put(expertLogin, rating);
    }

    @Override
    public Integer get(int applicationId, String expertLogin) {
        Map<String, Integer> m = db.getRatings().get(applicationId);
        return m == null ? null : m.get(expertLogin);
    }

    @Override
    public void remove(int applicationId, String expertLogin) {
        Map<String, Integer> m = db.getRatings().get(applicationId);
        if (m != null) {
            m.remove(expertLogin);
        }
    }

    @Override
    public void removeByApplication(int applicationId) {
        db.getRatings().remove(applicationId);
    }

    @Override
    public void removeByExpert(String expertLogin) {
        db.getRatings().values().forEach(m -> m.remove(expertLogin));
    }

    @Override
    public Map<Integer, Integer> getByExpert(String expertLogin) {
        Map<Integer, Integer> result = new HashMap<>();
        db.getRatings().forEach((id, m) -> {
            Integer r = m.get(expertLogin);
            if (r != null) {
                result.put(id, r);
            }
        });
        return result;
    }

    @Override
    public Map<String, Integer> getByApplication(int applicationId) {
        Map<String, Integer> m = db.getRatings().get(applicationId);
        return m == null ? new HashMap<>() : new HashMap<>(m);
    }
}
