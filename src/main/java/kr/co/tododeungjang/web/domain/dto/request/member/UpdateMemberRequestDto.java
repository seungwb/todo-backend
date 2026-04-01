package kr.co.tododeungjang.web.domain.dto.request.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMemberRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{11,13}$")
    private String phone;
}
