package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Application;

import java.util.Collection;
import java.util.Set;

public interface ApplicationDao {
    /** Stores a new application and returns its generated id. */
    int insert(String title, String description, Set<String> directions, long amount, String owner);

    Application get(int id);

    void delete(int id);

    Collection<Application> getAll();
}
