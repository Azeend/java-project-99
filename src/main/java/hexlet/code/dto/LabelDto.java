package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;

@Getter
@Setter
public class LabelDto {
    private long id;
    private JsonNullable<String> name;
    private Date createdAt;
}
