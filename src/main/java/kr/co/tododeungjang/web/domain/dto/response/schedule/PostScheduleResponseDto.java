package kr.co.tododeungjang.web.domain.dto.response.schedule;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostScheduleResponseDto extends ResponseDto {
    public PostScheduleResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostScheduleResponseDto> success() {
        PostScheduleResponseDto result = new PostScheduleResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
