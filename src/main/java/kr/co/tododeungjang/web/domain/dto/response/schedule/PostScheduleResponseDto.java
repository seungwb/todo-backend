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

    public static ResponseEntity<PostScheduleResponseDto> success (){
        PostScheduleResponseDto result = new PostScheduleResponseDto();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistedUser(){
        ResponseDto reslut = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslut);
    }

    public static ResponseEntity<ResponseDto> authorizationFail(){
        ResponseDto reslut = new ResponseDto(ResponseCode.AUTHORIZATION_FAIL, ResponseMessage.AUTHORIZATION_FAIL);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslut);
    }
}
