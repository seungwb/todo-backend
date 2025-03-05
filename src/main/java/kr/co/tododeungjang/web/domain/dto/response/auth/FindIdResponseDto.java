package kr.co.tododeungjang.web.domain.dto.response.auth;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class FindIdResponseDto extends ResponseDto {

    private final String email;

    public FindIdResponseDto(String email) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = email;
    }

    public static ResponseEntity<FindIdResponseDto> success(String email){
        FindIdResponseDto result = new FindIdResponseDto(email);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> findIdFail(){
        ResponseDto result = new ResponseDto(ResponseCode.FIND_ID_FAIL, ResponseMessage.FIND_ID_FAIL);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
