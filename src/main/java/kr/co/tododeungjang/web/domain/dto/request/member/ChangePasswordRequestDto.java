package kr.co.tododeungjang.web.domain.dto.request.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePasswordRequestDto {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 20)
    private String newPassword;
}
