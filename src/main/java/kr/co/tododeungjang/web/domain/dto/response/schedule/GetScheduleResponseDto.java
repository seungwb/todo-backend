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
}
