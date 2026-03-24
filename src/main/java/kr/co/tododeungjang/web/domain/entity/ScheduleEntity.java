package kr.co.tododeungjang.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime endDate;

    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime regDate;

    private Long memberId;

    public void update(String title, String content, String location, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
