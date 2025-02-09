package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.auth.ScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import kr.co.tododeungjang.web.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/schedule")
    public ResponseEntity<? super SignInResponseDto> createSchedule(
            @Valid @RequestBody ScheduleRequestDto requestBody,
            @AuthenticationPrincipal String email
            ){
        return calendarService.schedule(requestBody, email);
    }
}
