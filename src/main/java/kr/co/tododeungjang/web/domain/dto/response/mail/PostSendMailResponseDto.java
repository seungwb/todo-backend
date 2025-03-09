package kr.co.tododeungjang.web.domain.dto.response.mail;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostSendMailResponseDto extends ResponseDto {
    public PostSendMailResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostSendMailResponseDto> success(){
        PostSendMailResponseDto result = new PostSendMailResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
