package kr.co.tododeungjang.web.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Long id;
    private String image;
    private Long calendarId;
}
