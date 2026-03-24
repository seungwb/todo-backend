package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.mail.FindPasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.mail.VerifiedNumberRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostSendMailResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostVerificationNumberResponseDto;
import org.springframework.http.ResponseEntity;

public interface MailService {

    ResponseEntity<? super PostSendMailResponseDto> sendMail(FindPasswordRequestDto requestBody);

    ResponseEntity<? super PostVerificationNumberResponseDto> verificationNumber(VerifiedNumberRequestDto requestBody);
}
