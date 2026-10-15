package academy.tochkavhoda.competition.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

final class Validator {
    static final int MIN_LOGIN_LENGTH = 8;
    static final int MIN_PASSWORD_LENGTH = 8;

    private Validator() {
    }

    static String notBlank(String value, String field) throws ServiceException {
        if (value == null || value.isBlank()) {
            throw new ServiceException("Field '" + field + "' must not be empty");
        }
        return value.trim();
    }

    static String login(String login) throws ServiceException {
        String value = notBlank(login, "login");
        if (value.length() < MIN_LOGIN_LENGTH) {
            throw new ServiceException("Login must be at least " + MIN_LOGIN_LENGTH + " characters long");
        }
        return value;
    }

    static String password(String password) throws ServiceException {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new ServiceException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
        boolean digit = password.chars().anyMatch(Character::isDigit);
        boolean special = password.chars().anyMatch(c -> !Character.isLetterOrDigit(c) && !Character.isWhitespace(c));
        if (!digit || !special) {
            throw new ServiceException("Password must contain a digit and a special character");
        }
        return password;
    }

    /** Trims, lower-cases and de-duplicates directions; at least one non-blank direction is required. */
    static Set<String> directions(List<String> directions) throws ServiceException {
        if (directions == null || directions.isEmpty()) {
            throw new ServiceException("At least one direction is required");
        }
        Set<String> result = new LinkedHashSet<>();
        for (String d : directions) {
            if (d == null || d.isBlank()) {
                throw new ServiceException("Direction must not be empty");
            }
            result.add(d.trim().toLowerCase());
        }
        return result;
    }

    /** Optional filter: null or empty list means "no filter" (empty set returned). */
    static Set<String> optionalDirections(List<String> directions) throws ServiceException {
        if (directions == null || directions.isEmpty()) {
            return new LinkedHashSet<>();
        }
        return directions(directions);
    }
}
