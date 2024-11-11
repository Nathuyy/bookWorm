package bookworm.bookworm.DTO.genres;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GenreRegistrationDTO {
    @NotNull
    @NotBlank(message = "O tipo do gênero não pode estar vazio.")
    private String type;
}
