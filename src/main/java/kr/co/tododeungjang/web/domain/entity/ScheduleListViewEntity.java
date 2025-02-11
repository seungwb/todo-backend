package kr.co.tododeungjang.web.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedule_list_view")
public class ScheduleListViewEntity {
    @Id
    private Long id;

    private String name;

    private String title;

    private String content;

    private String location;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime regDate;

    private Long memberId;
}
