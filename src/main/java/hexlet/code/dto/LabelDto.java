package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LabelDto {
    private long id;
    private String name;
    private Date createdAt;
}
