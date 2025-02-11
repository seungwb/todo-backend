package kr.co.tododeungjang.web.domain.dto.response.schedule;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.object.ScheduleListItem;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetScheduleResponseDto extends ResponseDto {

    private final List<ScheduleListItem> scheduleListItems;
    public GetScheduleResponseDto(List<ScheduleListItem> scheduleListItems) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.scheduleListItems = scheduleListItems;
    }

    public static ResponseEntity<GetScheduleResponseDto> success (List<ScheduleListItem> scheduleListItems){
        GetScheduleResponseDto result = new GetScheduleResponseDto(scheduleListItems);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistedUser(){
        ResponseDto reslut = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reslut);
    }

    public static ResponseEntity<ResponseDto> authorizationFail(){
        ResponseDto reslut = new ResponseDto(ResponseCode.AUTHORIZATION_FAIL, ResponseMessage.AUTHORIZATION_FAIL);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reslut);
    }
}
