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
public class GetTodayScheduleResponseDto extends ResponseDto {

    private final List<ScheduleListItem> todayScheduleListItems;

    public GetTodayScheduleResponseDto(List<ScheduleListItem> todayScheduleListItems) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.todayScheduleListItems = todayScheduleListItems;
    }

    public static ResponseEntity<GetTodayScheduleResponseDto> success(List<ScheduleListItem> todayScheduleListItems) {
        GetTodayScheduleResponseDto result = new GetTodayScheduleResponseDto(todayScheduleListItems);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
