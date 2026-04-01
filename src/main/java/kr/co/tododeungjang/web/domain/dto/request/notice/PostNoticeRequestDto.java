package kr.co.tododeungjang.web.domain.dto.request.notice;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostNoticeRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
