package kr.co.tododeungjang.web.domain.dto.request.todo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostTodoRequestDto {

    @NotBlank
    private String title;

    private String content;
}
