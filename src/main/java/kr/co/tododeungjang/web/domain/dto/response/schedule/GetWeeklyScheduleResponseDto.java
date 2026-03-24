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
public class GetWeeklyScheduleResponseDto extends ResponseDto {

    private final List<ScheduleListItem> weeklyScheduleListItems;

    public GetWeeklyScheduleResponseDto(List<ScheduleListItem> weeklyScheduleListItems) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.weeklyScheduleListItems = weeklyScheduleListItems;
    }

    public static ResponseEntity<GetWeeklyScheduleResponseDto> success(List<ScheduleListItem> weeklyScheduleListItems) {
        GetWeeklyScheduleResponseDto result = new GetWeeklyScheduleResponseDto(weeklyScheduleListItems);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
