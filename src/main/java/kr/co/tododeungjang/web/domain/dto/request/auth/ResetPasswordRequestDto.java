package kr.co.tododeungjang.web.domain.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String newPassword;
}
