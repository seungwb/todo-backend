package kr.co.tododeungjang.web.domain.dto.response;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFailed(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedTodo(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_TODO, ResponseMessage.NOT_EXISTED_TODO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedSchedule(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_SCHEDULE, ResponseMessage.NOT_EXISTED_SCHEDULE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedNotice(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_NOTICE, ResponseMessage.NOT_EXISTED_NOTICE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
