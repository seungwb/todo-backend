package kr.co.tododeungjang.web.domain.dto.request.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VerifiedNumberRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String number;
}
