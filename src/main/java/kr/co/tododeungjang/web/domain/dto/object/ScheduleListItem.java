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
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private LocalDateTime startDate;
    @NotBlank
    private LocalDateTime endDate;
    private String location;
    @NotBlank
    private LocalDateTime regDate;
}
