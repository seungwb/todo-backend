package kr.co.tododeungjang.web.domain.entity;

import jakarta.persistence.*;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateStateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateTodoRequestDto;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private OffsetDateTime regDate;

    private Boolean state;

    private Long memberId;

    public void updateState(UpdateStateTodoRequestDto requestBody) {
        this.state = requestBody.getState();
    }

    public void update(UpdateTodoRequestDto requestBody) {
        this.title = requestBody.getTitle();
        this.content = requestBody.getContent();
    }
}
