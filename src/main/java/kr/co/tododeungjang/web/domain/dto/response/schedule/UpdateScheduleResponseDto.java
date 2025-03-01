package kr.co.tododeungjang.web.domain.dto.response.schedule;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class UpdateScheduleResponseDto extends ResponseDto {
    public UpdateScheduleResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<UpdateScheduleResponseDto> success (){
        UpdateScheduleResponseDto result = new UpdateScheduleResponseDto();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistedUser(){
        ResponseDto reslut = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reslut);
    }

    public static ResponseEntity<ResponseDto> notExistedSchedule(){
        ResponseDto reslut = new ResponseDto(ResponseCode.NOT_EXISTED_SCHEDULE, ResponseMessage.NOT_EXISTED_SCHEDULE);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reslut);
    }
}
