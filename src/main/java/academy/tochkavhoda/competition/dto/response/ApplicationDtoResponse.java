package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public record ApplicationDtoResponse(int id, String title, String description, List<String> directions, long amount,
                                     String ownerLogin, Integer rating) {
}
