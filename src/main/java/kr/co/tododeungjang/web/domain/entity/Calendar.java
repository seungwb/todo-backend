package kr.co.tododeungjang.web.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

    private Long id;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;
    private String location;
    private Date regDate;
    private Long memberId;

}
