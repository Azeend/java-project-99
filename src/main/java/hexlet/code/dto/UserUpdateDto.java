package hexlet.code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class UserUpdateDto {
    @NotNull
    @Email
    private JsonNullable<String> email;
    @NotNull(message = "Password must be atleast 3 characters")
    @Size(min = 3)
    private JsonNullable<String> passwordDigest;
    private JsonNullable<String> firstName;
    private JsonNullable<String> lastName;
}
