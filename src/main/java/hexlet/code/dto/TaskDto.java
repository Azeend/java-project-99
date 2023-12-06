package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TaskDto {
    private long id;
    private JsonNullable<Integer> index;
    private JsonNullable<String> title;
    private JsonNullable<String> content;
    private JsonNullable<String> status;
    private JsonNullable<Long> assignee_id;
    private Date createdAt;
    private JsonNullable<Set<Long>> taskLabelIds;
}