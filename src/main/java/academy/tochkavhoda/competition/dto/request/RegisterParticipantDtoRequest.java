package academy.tochkavhoda.competition.dto.request;

public record RegisterParticipantDtoRequest(String firstName, String lastName, String firmName, String login,
                                            String password) {
}
