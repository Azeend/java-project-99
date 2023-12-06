package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private Long id;
    private JsonNullable<String> firstName;
    private JsonNullable<String> lastName;
    private JsonNullable<String> email;
    private LocalDate createdAt;
}
