package kr.co.tododeungjang.web.domain.dto.response.notice;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.object.NoticeListItem;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetNoticeListResponseDto extends ResponseDto {

    private final List<NoticeListItem> noticeList;

    private GetNoticeListResponseDto(List<NoticeListItem> noticeList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.noticeList = noticeList;
    }

    public static ResponseEntity<GetNoticeListResponseDto> success(List<NoticeListItem> noticeList) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetNoticeListResponseDto(noticeList));
    }
}
