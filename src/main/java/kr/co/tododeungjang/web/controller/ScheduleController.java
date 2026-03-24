package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.schedule.UpdateScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.DeleteScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetTodayScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetWeeklyScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.PostScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.UpdateScheduleResponseDto;
import kr.co.tododeungjang.web.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("")
    public ResponseEntity<? super PostScheduleResponseDto> createSchedule(
            @Valid @RequestBody PostScheduleRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return scheduleService.saveSchedule(requestBody, email);
    }

    @GetMapping("")
    public ResponseEntity<? super GetScheduleResponseDto> getSchedule(
            @AuthenticationPrincipal String email) {
        return scheduleService.getSchedule(email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? super DeleteScheduleResponseDto> deleteSchedule(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal String email) {
        return scheduleService.deleteSchedule(id, email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? super UpdateScheduleResponseDto> updateSchedule(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateScheduleRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return scheduleService.updateSchedule(id, email, requestBody);
    }

    @GetMapping("/today")
    public ResponseEntity<? super GetTodayScheduleResponseDto> getTodaySchedule(
            @RequestParam(name = "today", required = false) String today,
            @AuthenticationPrincipal String email) {
        return scheduleService.getTodaySchedule(today, email);
    }

    @GetMapping("/weekly")
    public ResponseEntity<? super GetWeeklyScheduleResponseDto> getWeeklySchedule(
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @AuthenticationPrincipal String email) {
        return scheduleService.getWeeklySchedule(start, end, email);
    }
}
