package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
public class TaskStatusDto {
    private Long id;
    private JsonNullable<String> name;
    private JsonNullable<String> slug;
    private LocalDate createdAt;
}
