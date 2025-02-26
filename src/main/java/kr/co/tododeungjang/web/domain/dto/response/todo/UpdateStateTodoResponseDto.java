package kr.co.tododeungjang.web.domain.dto.response.todo;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UpdateStateTodoResponseDto extends ResponseDto {
    public UpdateStateTodoResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<UpdateStateTodoResponseDto> success(){
        UpdateStateTodoResponseDto result = new UpdateStateTodoResponseDto();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
