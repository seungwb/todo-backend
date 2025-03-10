package kr.co.tododeungjang.web.service.implement;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import kr.co.tododeungjang.web.domain.dto.request.mail.FindPasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.mail.VerifiedNumberRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostSendMailResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.mail.PostVerificationNumberResponseDto;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImplement {

    @Resource
    private final JavaMailSender mailSender; //의존성 주입 문제
    private final MemberRepository memberRepository;
    private static String verificationNumber;

    @Value("${spring.mail.username}")
    private String fromMail;

    public ResponseEntity<? super PostSendMailResponseDto> sendMail(FindPasswordRequestDto requestBody){
        try {
            String toMail = requestBody.getEmail();
            MemberEntity member = memberRepository.findByEmail(toMail);
            if (member == null) return PostSendMailResponseDto.notExistedUser();
            Random random = new Random();
            verificationNumber = String.format("%06d", random.nextInt(1000000));
            String subject = "비밀번호 찾기 인증번호";
            String text = "인증번호: " + verificationNumber + "\n인증번호를 입력해주세요.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(fromMail);

            mailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
            ResponseDto.databaseError();
        }

        return PostSendMailResponseDto.success();
    }

    public ResponseEntity<? super PostVerificationNumberResponseDto> verificationNumber(VerifiedNumberRequestDto requestBody){
        try {
            String number = requestBody.getNumber();
            if(!number.equals(verificationNumber)) return PostVerificationNumberResponseDto.notMatchNumber();
        }catch (Exception e){
            e.printStackTrace();
            ResponseDto.databaseError();
        }
        return PostVerificationNumberResponseDto.success();
    }
}
