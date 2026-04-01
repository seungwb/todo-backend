package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.member.ChangePasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.member.UpdateMemberRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.member.WithdrawRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.member.*;
import kr.co.tododeungjang.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<? super GetMemberResponseDto> getMe(
            @AuthenticationPrincipal String email) {
        return memberService.getMe(email);
    }

    @PutMapping("/me")
    public ResponseEntity<? super UpdateMemberResponseDto> updateMe(
            @Valid @RequestBody UpdateMemberRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return memberService.updateMe(requestBody, email);
    }

    @PutMapping("/password")
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(
            @Valid @RequestBody ChangePasswordRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return memberService.changePassword(requestBody, email);
    }

    @DeleteMapping("/me")
    public ResponseEntity<? super WithdrawResponseDto> withdraw(
            @Valid @RequestBody WithdrawRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        return memberService.withdraw(requestBody, email);
    }
}
