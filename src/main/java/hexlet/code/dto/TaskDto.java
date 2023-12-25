package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.time.LocalDate;
@Getter
@Setter
public class TaskDto {
    private Long id;
    private String title;
    private Integer index;
    private String content;
    private String status;
    private Long assignee_id;
    private Set<Long> taskLabelIds;
    private LocalDate createdAt;
}
