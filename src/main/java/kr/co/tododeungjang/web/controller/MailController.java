package kr.co.tododeungjang.web.controller;

import kr.co.tododeungjang.web.domain.dto.response.mail.PostSendMailResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostVerificationNumberResponseDto;
import kr.co.tododeungjang.web.service.implement.MailServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailServiceImplement mailService;

    @PostMapping("/send")
    public ResponseEntity<? super PostSendMailResponseDto> sendMail(
            @RequestParam(name = "email") String toEmail
    ){
        return mailService.sendMail(toEmail);
    }
    @PostMapping("/verified")
    public ResponseEntity<? super PostVerificationNumberResponseDto> verificationNumber(
            @RequestParam(name = "number") String number
    ){
        return mailService.verificationNumber(number);
    }
}
