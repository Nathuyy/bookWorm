package DTO.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 4)
    private String password;

    private String image;
}
