package kr.co.tododeungjang.web.domain.dto.object;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListItem {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    private String content;

    @NotBlank
    private OffsetDateTime regDate;

    @NotBlank
    private Boolean state;
}
