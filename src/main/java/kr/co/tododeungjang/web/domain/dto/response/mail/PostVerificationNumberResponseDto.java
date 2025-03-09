package kr.co.tododeungjang.web.domain.dto.response.mail;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostVerificationNumberResponseDto extends ResponseDto {
    public PostVerificationNumberResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostVerificationNumberResponseDto> success(){
        PostVerificationNumberResponseDto reslut = new PostVerificationNumberResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reslut);
    }

    public static ResponseEntity<ResponseDto> notMatchNumber(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_MATCH_NUMBER, ResponseMessage.NOT_MATCH_NUMBER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
