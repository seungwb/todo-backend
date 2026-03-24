package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.request.auth.FindIdRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignInRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignUpRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.FindIdResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignUpResponseDto;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.domain.entity.MemberRole;
import kr.co.tododeungjang.web.provider.JwtProvider;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.repository.MemberRoleRepository;
import kr.co.tododeungjang.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImplement.class);

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {
            String name = dto.getName();
            String email = dto.getEmail();
            boolean existedEmail = memberRepository.existsByEmail(email);
            if (existedEmail) {
                return SignUpResponseDto.duplicateEmail();
            }
            String phone = dto.getPhone();
            boolean existedPhone = memberRepository.existsByPhone(phone);
            if (existedPhone) {
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

            MemberEntity saveMember = memberRepository.save(memberEntity);

            MemberRole role = MemberRole.builder()
                    .roleName("ROLE_MEMBER")
                    .memberId(saveMember.getId())
                    .build();

            memberRoleRepository.save(role);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        int expirationTime = 0;
        try {
            String email = dto.getEmail();
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if (memberEntity == null) {
                return SignInResponseDto.signInFailed();
            }

            String password = dto.getPassword();
            String encodedPassword = memberEntity.getPassword();

            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) {
                return SignInResponseDto.signInFailed();
            }

            log.debug("로그인 email={}", email);

            MemberRole memberRole = memberRoleRepository.findByMemberId(memberEntity.getId());
            String roleName = memberRole != null ? memberRole.getRoleName() : "ROLE_MEMBER";
            token = jwtProvider.create(email, roleName);
            expirationTime = jwtProvider.getExpirationTime(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token, expirationTime);
    }

    @Override
    public ResponseEntity<? super FindIdResponseDto> findId(FindIdRequestDto dto) {
        String email;
        try {
            String name = dto.getName();
            String phone = dto.getPhone();
            MemberEntity memberEntity = memberRepository.findByNameAndPhone(name, phone);
            if (memberEntity == null) return FindIdResponseDto.findIdFail();
            email = memberEntity.getEmail();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return FindIdResponseDto.success(email);
    }
}
