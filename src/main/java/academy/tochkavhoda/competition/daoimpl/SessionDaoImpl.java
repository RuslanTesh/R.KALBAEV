package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.SessionDao;
import academy.tochkavhoda.competition.database.Database;

import java.util.UUID;

public class SessionDaoImpl implements SessionDao {
    private final Database db = Database.getInstance();

    @Override
    public String create(String login) {
        String token = UUID.randomUUID().toString();
        db.getTokens().put(token, login);
        return token;
    }

    @Override
    public String getLogin(String token) {
        return token == null ? null : db.getTokens().get(token);
    }

    @Override
    public void remove(String token) {
        db.getTokens().remove(token);
    }

    @Override
    public void removeByLogin(String login) {
        db.getTokens().values().removeIf(login::equals);
    }
}
