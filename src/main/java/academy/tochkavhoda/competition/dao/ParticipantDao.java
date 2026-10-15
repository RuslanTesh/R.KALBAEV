package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Participant;

import java.util.Collection;

public interface ParticipantDao {
    void insert(Participant participant);

    Participant get(String login);

    void delete(String login);

    Collection<Participant> getAll();
}
