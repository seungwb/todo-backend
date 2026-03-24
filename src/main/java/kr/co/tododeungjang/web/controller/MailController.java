package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.mail.FindPasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.mail.VerifiedNumberRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostSendMailResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostVerificationNumberResponseDto;
import kr.co.tododeungjang.web.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<? super PostSendMailResponseDto> sendMail(
            @Valid @RequestBody FindPasswordRequestDto requestBody) {
        return mailService.sendMail(requestBody);
    }

    @PostMapping("/verified")
    public ResponseEntity<? super PostVerificationNumberResponseDto> verificationNumber(
            @Valid @RequestBody VerifiedNumberRequestDto requestBody) {
        return mailService.verificationNumber(requestBody);
    }
}
