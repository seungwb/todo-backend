package kr.co.tododeungjang.web.domain.dto.object;

import kr.co.tododeungjang.web.domain.entity.NoticeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeListItem {

    private Long id;
    private String title;
    private LocalDateTime regDate;
    private Long viewCount;

    public NoticeListItem(NoticeEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.regDate = entity.getRegDate();
        this.viewCount = entity.getViewCount();
    }
}
