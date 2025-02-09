package kr.co.tododeungjang.web.service;


import kr.co.tododeungjang.web.domain.dto.request.auth.ScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import org.springframework.http.ResponseEntity;

public interface CalendarService {
    ResponseEntity<? super SignInResponseDto> schedule(ScheduleRequestDto dto, String token);
}
