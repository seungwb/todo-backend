package kr.co.tododeungjang.web.domain.dto.request.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String name;

    @NotBlank @Email
    private String email;

    @NotBlank @Pattern(regexp = "^[0-9]{11,13}$")
    private String phone;

    @NotNull @Size(min = 8, max = 20)
    private String password;
}
