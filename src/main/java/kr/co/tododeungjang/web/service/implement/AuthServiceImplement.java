package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.request.auth.SignUpRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignUpResponseDto;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final MemberRepository memberRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {


        try {
            String name = dto.getName();
            String email = dto.getEmail();
            boolean existedEmail = memberRepository.existsByEmail(email);
            if (existedEmail){
                return SignUpResponseDto.duplicateEmail();
            }
            String phone = dto.getPhone();
            boolean existedPhone = memberRepository.existsByEmail(email);
            if (existedPhone){
                return SignUpResponseDto.duplicatePhone();
            }

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);


            LocalDateTime joinDate = LocalDateTime.now();
            MemberEntity memberEntity = MemberEntity.builder()
                    .email(email)
                    .phone(phone)
                    .name(name)
                    .password(encodedPassword)
                    .joinDate(joinDate)
                    .build();

            memberRepository.save(memberEntity);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }
}
