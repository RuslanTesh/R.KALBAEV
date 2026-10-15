package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.RatingDao;
import academy.tochkavhoda.competition.daoimpl.ApplicationDaoImpl;
import academy.tochkavhoda.competition.daoimpl.RatingDaoImpl;
import academy.tochkavhoda.competition.dto.request.ApplicationIdDtoRequest;
import academy.tochkavhoda.competition.dto.request.RateApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.response.ApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationsDtoResponse;
import academy.tochkavhoda.competition.dto.response.EmptyDtoResponse;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatingService {
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;

    private final AuthService auth = new AuthService();
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final RatingDao ratingDao = new RatingDaoImpl();

    /** Sets or changes the expert's rating of an application. */
    public EmptyDtoResponse rate(String token, RateApplicationDtoRequest r) throws ServiceException {
        Expert e = auth.expert(token);
        auth.checkOpen();
        if (r.rating() < MIN_RATING || r.rating() > MAX_RATING) {
            throw new ServiceException("Rating must be between " + MIN_RATING + " and " + MAX_RATING);
        }
        Application a = applicationDao.get(r.applicationId());
        if (a == null) {
            throw new ServiceException("Application not found");
        }
        if (Collections.disjoint(a.directions(), e.directions())) {
            throw new ServiceException("Expert is not competent in the directions of this application");
        }
        ratingDao.put(a.id(), e.login(), r.rating());
        return new EmptyDtoResponse();
    }

    public EmptyDtoResponse remove(String token, ApplicationIdDtoRequest r) throws ServiceException {
        Expert e = auth.expert(token);
        auth.checkOpen();
        if (ratingDao.get(r.applicationId(), e.login()) == null) {
            throw new ServiceException("Rating not found");
        }
        ratingDao.remove(r.applicationId(), e.login());
        return new EmptyDtoResponse();
    }

    /** All applications rated by the expert, with his ratings. */
    public ApplicationsDtoResponse getMyRatings(String token) throws ServiceException {
        Expert e = auth.expert(token);
        List<ApplicationDtoResponse> result = new ArrayList<>();
        ratingDao.getByExpert(e.login()).forEach((id, rating) -> {
            Application a = applicationDao.get(id);
            if (a != null) {
                result.add(ApplicationService.toDto(a, rating));
            }
        });
        result.sort((x, y) -> Integer.compare(x.id(), y.id()));
        return new ApplicationsDtoResponse(result);
    }
}
