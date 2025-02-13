package kr.co.tododeungjang.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.tododeungjang.web.domain.dto.request.schedule.UpdateScheduleRequestDto;
import lombok.*;

import java.time.LocalDateTime;
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

    public void update(UpdateScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.location = dto.getLocation();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }
}
