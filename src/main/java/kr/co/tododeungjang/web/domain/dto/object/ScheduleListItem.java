package kr.co.tododeungjang.web.domain.dto.object;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleListItem {
    @NotBlank
    private String name;

    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private LocalDateTime regDate;
}
