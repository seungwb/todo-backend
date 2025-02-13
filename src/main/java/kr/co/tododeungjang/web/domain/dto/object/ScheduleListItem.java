package kr.co.tododeungjang.web.domain.dto.object;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
    private OffsetDateTime startDate;
    @NotBlank
    private OffsetDateTime endDate;
    private String location;
    @NotBlank
    private OffsetDateTime regDate;
}
