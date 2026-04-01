package kr.co.tododeungjang.web.domain.dto.response.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.entity.NoticeEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetNoticeResponseDto extends ResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long memberId;
    private final Long viewCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime regDate;

    private GetNoticeResponseDto(NoticeEntity entity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMemberId();
        this.viewCount = entity.getViewCount();
        this.regDate = entity.getRegDate();
    }

    public static ResponseEntity<GetNoticeResponseDto> success(NoticeEntity entity) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetNoticeResponseDto(entity));
    }
}
