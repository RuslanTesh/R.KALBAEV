package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public record AddApplicationDtoRequest(String title, String description, List<String> directions, long amount) {
}
