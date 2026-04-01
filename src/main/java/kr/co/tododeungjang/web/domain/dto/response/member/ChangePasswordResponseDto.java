package kr.co.tododeungjang.web.domain.dto.response.member;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ChangePasswordResponseDto extends ResponseDto {

    private ChangePasswordResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<ChangePasswordResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new ChangePasswordResponseDto());
    }

    public static ResponseEntity<ResponseDto> wrongPassword() {
        ResponseDto result = new ResponseDto(ResponseCode.WRONG_PASSWORD, ResponseMessage.WRONG_PASSWORD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
