package kr.co.tododeungjang.web.domain.dto.response.todo;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DeleteTodoResponseDto extends ResponseDto {
    public DeleteTodoResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super DeleteTodoResponseDto> success(){
        DeleteTodoResponseDto result = new DeleteTodoResponseDto();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistedUser(){
        ResponseDto reslut = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reslut);
    }

    public static ResponseEntity<ResponseDto> notExistedTodo(){
        ResponseDto reslut = new ResponseDto(ResponseCode.NOT_EXISTED_TODO, ResponseMessage.NOT_EXISTED_TODO);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reslut);
    }
}
