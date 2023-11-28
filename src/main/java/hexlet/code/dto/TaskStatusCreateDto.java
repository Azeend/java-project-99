package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusCreateDto {
    private String name;
    private String slug;
}
