package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Expert;

import java.util.Collection;

public interface ExpertDao {
    void insert(Expert expert);

    Expert get(String login);

    void delete(String login);

    Collection<Expert> getAll();
}
