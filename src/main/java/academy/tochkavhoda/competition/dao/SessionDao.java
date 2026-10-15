package academy.tochkavhoda.competition.dao;

public interface SessionDao {
    String create(String login);

    String getLogin(String token);

    void remove(String token);

    void removeByLogin(String login);
}
