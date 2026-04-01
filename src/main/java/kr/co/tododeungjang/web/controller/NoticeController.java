package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.notice.PostNoticeRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.notice.UpdateNoticeRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.notice.*;
import kr.co.tododeungjang.web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("")
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList() {
        return noticeService.getNoticeList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? super GetNoticeResponseDto> getNotice(
            @PathVariable("id") Long id) {
        return noticeService.getNotice(id);
    }

    @PostMapping("")
    public ResponseEntity<? super PostNoticeResponseDto> postNotice(
            @Valid @RequestBody PostNoticeRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return noticeService.postNotice(requestBody, email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? super UpdateNoticeResponseDto> updateNotice(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateNoticeRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return noticeService.updateNotice(id, requestBody, email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? super DeleteNoticeResponseDto> deleteNotice(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal String email) {
        return noticeService.deleteNotice(id, email);
    }
}
