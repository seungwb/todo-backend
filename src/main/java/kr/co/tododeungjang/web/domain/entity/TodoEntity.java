package kr.co.tododeungjang.web.domain.entity;

import jakarta.persistence.*;
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

    public void updateState(Boolean state) {
        this.state = state;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
