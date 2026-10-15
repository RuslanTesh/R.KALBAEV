package academy.tochkavhoda.competition.dao;

import java.util.Map;

public interface RatingDao {
    void put(int applicationId, String expertLogin, int rating);

    Integer get(int applicationId, String expertLogin);

    void remove(int applicationId, String expertLogin);

    void removeByApplication(int applicationId);

    void removeByExpert(String expertLogin);

    /** Application id to the rating given by the expert. */
    Map<Integer, Integer> getByExpert(String expertLogin);

    /** Expert login to the rating of the application. */
    Map<String, Integer> getByApplication(int applicationId);
}
