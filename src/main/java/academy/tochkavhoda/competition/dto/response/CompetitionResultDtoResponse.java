package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public record CompetitionResultDtoResponse(List<GrantDtoResponse> grants, long remaining) {
}
