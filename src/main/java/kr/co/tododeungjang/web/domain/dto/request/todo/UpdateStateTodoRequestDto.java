package kr.co.tododeungjang.web.domain.dto.request.todo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStateTodoRequestDto {

    @NotNull
    private Boolean state;
}
