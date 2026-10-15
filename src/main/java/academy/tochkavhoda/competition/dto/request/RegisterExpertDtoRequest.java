package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public record RegisterExpertDtoRequest(String firstName, String lastName, List<String> directions, String login,
                                       String password) {
}
