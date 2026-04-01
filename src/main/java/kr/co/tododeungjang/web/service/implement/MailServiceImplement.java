package kr.co.tododeungjang.web.service.implement;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import kr.co.tododeungjang.web.common.VerificationStore;
import kr.co.tododeungjang.web.domain.dto.request.mail.FindPasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.mail.VerifiedNumberRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostSendMailResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostVerificationNumberResponseDto;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class MailServiceImplement implements MailService {

    @Resource
    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private final VerificationStore verificationStore;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public ResponseEntity<? super PostSendMailResponseDto> sendMail(FindPasswordRequestDto requestBody) {
        try {
            String toMail = requestBody.getEmail();
            MemberEntity member = memberRepository.findByEmail(toMail);
            if (member == null) return PostSendMailResponseDto.notExistedUser();

            String verificationNumber = String.format("%06d", secureRandom.nextInt(1000000));
            verificationStore.putCode(toMail, verificationNumber);

            String subject = "비밀번호 찾기 인증번호";
            String text = "인증번호: " + verificationNumber + "\n인증번호를 입력해주세요.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(fromMail);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostSendMailResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostVerificationNumberResponseDto> verificationNumber(VerifiedNumberRequestDto requestBody) {
        try {
            String email = requestBody.getEmail();
            String number = requestBody.getNumber();
            String storedNumber = verificationStore.getCode(email);
            if (storedNumber == null || !number.equals(storedNumber)) {
                return PostVerificationNumberResponseDto.notMatchNumber();
            }
            verificationStore.removeCode(email);
            verificationStore.markVerified(email);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostVerificationNumberResponseDto.success();
    }
}
