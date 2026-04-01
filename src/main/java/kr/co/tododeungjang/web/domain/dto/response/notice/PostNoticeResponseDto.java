package kr.co.tododeungjang.web.domain.dto.response.notice;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostNoticeResponseDto extends ResponseDto {

    private PostNoticeResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostNoticeResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PostNoticeResponseDto());
    }
}
