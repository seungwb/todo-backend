package kr.co.tododeungjang.web.service;


import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.PostScheduleResponseDto;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    ResponseEntity<? super PostScheduleResponseDto> schedule(PostScheduleRequestDto dto, String token);
}
