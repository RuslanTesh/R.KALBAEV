package academy.tochkavhoda.competition.model;

import java.util.Set;

public record Application(int id, String title, String description, Set<String> directions, long amount,
                          String ownerLogin) {
}
