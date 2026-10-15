package academy.tochkavhoda.competition.model;

import java.util.Set;

public record Expert(String firstName, String lastName, Set<String> directions, String login, String password) {
}
