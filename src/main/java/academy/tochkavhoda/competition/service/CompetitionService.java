package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.CompetitionDao;
import academy.tochkavhoda.competition.dao.RatingDao;
import academy.tochkavhoda.competition.daoimpl.ApplicationDaoImpl;
import academy.tochkavhoda.competition.daoimpl.CompetitionDaoImpl;
import academy.tochkavhoda.competition.daoimpl.RatingDaoImpl;
import academy.tochkavhoda.competition.dto.request.FinishCompetitionDtoRequest;
import academy.tochkavhoda.competition.dto.response.CompetitionResultDtoResponse;
import academy.tochkavhoda.competition.dto.response.GrantDtoResponse;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Grant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CompetitionService {
    private final AuthService auth = new AuthService();
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final RatingDao ratingDao = new RatingDaoImpl();
    private final CompetitionDao competitionDao = new CompetitionDaoImpl();
    private final Random random;

    public CompetitionService(Random random) {
        this.random = random;
    }

    /**
     * Closes the competition and distributes the fund. Applications are ranked by average rating (descending),
     * then by requested amount (ascending), then randomly. Applications with average rating not greater than the
     * threshold are not funded; applications that do not fit into the remaining fund are skipped.
     * A repeated call returns the stored result.
     */
    public CompetitionResultDtoResponse finish(String token, FinishCompetitionDtoRequest r) throws ServiceException {
        auth.loginOf(token);
        if (competitionDao.isFinished()) {
            return currentResult();
        }
        if (r.fund() < 0) {
            throw new ServiceException("Fund must not be negative");
        }
        if (r.threshold() < 0 || r.threshold() > RatingService.MAX_RATING) {
            throw new ServiceException("Threshold must be between 0 and " + RatingService.MAX_RATING);
        }
        record Candidate(Application application, double average) {
        }
        List<Candidate> candidates = new ArrayList<>();
        for (Application a : applicationDao.getAll()) {
            Map<String, Integer> ratings = ratingDao.getByApplication(a.id());
            double avg = ratings.values().stream().mapToInt(Integer::intValue).average().orElse(0);
            if (avg > r.threshold()) {
                candidates.add(new Candidate(a, avg));
            }
        }
        Collections.shuffle(candidates, random);
        candidates.sort(Comparator.comparingDouble(Candidate::average).reversed()
                .thenComparingLong(c -> c.application().amount()));
        long left = r.fund();
        List<Grant> grants = new ArrayList<>();
        for (Candidate c : candidates) {
            Application a = c.application();
            if (a.amount() <= left) {
                left -= a.amount();
                grants.add(new Grant(a.id(), a.title(), a.ownerLogin(), a.amount()));
            }
        }
        competitionDao.finish(grants, left);
        return currentResult();
    }

    private CompetitionResultDtoResponse currentResult() {
        List<GrantDtoResponse> grants = new ArrayList<>();
        for (Grant g : competitionDao.getGrants()) {
            grants.add(new GrantDtoResponse(g.applicationId(), g.title(), g.ownerLogin(), g.amount()));
        }
        return new CompetitionResultDtoResponse(grants, competitionDao.getRemaining());
    }
}
