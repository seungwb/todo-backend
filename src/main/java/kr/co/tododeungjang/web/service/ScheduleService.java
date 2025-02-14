package kr.co.tododeungjang.web.service;


import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.schedule.UpdateScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.GetTodayScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.GetWeeklyScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.DeleteScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.PostScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.UpdateScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

public interface ScheduleService {
    ResponseEntity<? super PostScheduleResponseDto> saveSchedule(PostScheduleRequestDto dto, String email);

    ResponseEntity<? super GetScheduleResponseDto> getSchedule(String email);

    ResponseEntity<? super DeleteScheduleResponseDto> deleteSchedule(Long id, String email);

    ResponseEntity<? super UpdateScheduleResponseDto> updateSchedule(Long id, String email, UpdateScheduleRequestDto requestBody);

    ResponseEntity<? super GetTodayScheduleResponseDto> getTodaySchedule(String today, String email);

    ResponseEntity<? super GetWeeklyScheduleResponseDto> getWeeklySchedule(String start, String end, String email);
}
