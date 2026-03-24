package kr.co.tododeungjang.web.domain.dto.object;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListItem {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    private String content;

    @NotNull
    private OffsetDateTime regDate;

    @NotNull
    private Boolean state;
}
