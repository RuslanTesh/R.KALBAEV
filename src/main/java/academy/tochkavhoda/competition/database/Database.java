package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Grant;
import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** In-memory storage of the whole server state (singleton). */
public class Database {

    private static final Database INSTANCE = new Database();

    private final Map<String, Participant> participants = new LinkedHashMap<>();
    private final Map<String, Expert> experts = new LinkedHashMap<>();
    private final Map<String, String> tokens = new HashMap<>();
    private final Map<Integer, Application> applications = new LinkedHashMap<>();
    private final Map<Integer, Map<String, Integer>> ratings = new HashMap<>();
    private int nextApplicationId = 1;
    private boolean finished;
    private List<Grant> grants = new ArrayList<>();
    private long remaining;

    private Database() {
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Map<String, Participant> getParticipants() {
        return participants;
    }

    public Map<String, Expert> getExperts() {
        return experts;
    }

    public Map<String, String> getTokens() {
        return tokens;
    }

    public Map<Integer, Application> getApplications() {
        return applications;
    }

    public Map<Integer, Map<String, Integer>> getRatings() {
        return ratings;
    }

    public int nextApplicationId() {
        return nextApplicationId++;
    }

    public boolean isFinished() {
        return finished;
    }

    public List<Grant> getGrants() {
        return grants;
    }

    public long getRemaining() {
        return remaining;
    }

    public void finish(List<Grant> grants, long remaining) {
        this.finished = true;
        this.grants = grants;
        this.remaining = remaining;
    }

    public void clear() {
        participants.clear();
        experts.clear();
        tokens.clear();
        applications.clear();
        ratings.clear();
        nextApplicationId = 1;
        finished = false;
        grants = new ArrayList<>();
        remaining = 0;
    }
}
