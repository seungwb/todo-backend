package kr.co.tododeungjang.web.service;


import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.DeleteScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.PostScheduleResponseDto;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    ResponseEntity<? super PostScheduleResponseDto> saveSchedule(PostScheduleRequestDto dto, String email);

    ResponseEntity<? super GetScheduleResponseDto> getSchedule(String email);

    ResponseEntity<? super DeleteScheduleResponseDto> deleteSchedule(Long id, String email);
}
