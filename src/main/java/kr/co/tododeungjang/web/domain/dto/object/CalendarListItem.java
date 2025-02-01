package kr.co.tododeungjang.web.domain.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarListItem {
    private Long id;
    private String name;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private String location;
    private String regDate;
    private String image;
}
