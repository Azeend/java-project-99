package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDto {
    private long id;
    private int index;
    private String title;
    private String content;
    private String status;
    private long assignee_id;
    private Date createdAt;
}
