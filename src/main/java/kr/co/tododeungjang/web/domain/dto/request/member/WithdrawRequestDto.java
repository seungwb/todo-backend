package kr.co.tododeungjang.web.domain.dto.request.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawRequestDto {

    @NotBlank
    private String password;
}
