package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.request.member.ChangePasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.member.UpdateMemberRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.member.WithdrawRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignUpResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.member.*;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.repository.MemberRoleRepository;
import kr.co.tododeungjang.web.repository.TodoRepository;
import kr.co.tododeungjang.web.repository.ScheduleRepository;
import kr.co.tododeungjang.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImplement implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final TodoRepository todoRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<? super GetMemberResponseDto> getMe(String email) {
        MemberEntity member;
        try {
            if (email == null) return ResponseDto.validationFailed();
            member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMemberResponseDto.success(member);
    }

    @Override
    @Transactional
    public ResponseEntity<? super UpdateMemberResponseDto> updateMe(UpdateMemberRequestDto dto, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            boolean duplicatePhone = memberRepository.existsByPhoneAndIdNot(dto.getPhone(), member.getId());
            if (duplicatePhone) return SignUpResponseDto.duplicatePhone();

            member.update(dto.getName(), dto.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UpdateMemberResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(ChangePasswordRequestDto dto, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            if (!passwordEncoder.matches(dto.getCurrentPassword(), member.getPassword())) {
                return ChangePasswordResponseDto.wrongPassword();
            }

            member.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ChangePasswordResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super WithdrawResponseDto> withdraw(WithdrawRequestDto dto, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
                return ChangePasswordResponseDto.wrongPassword();
            }

            Long memberId = member.getId();
            todoRepository.deleteByMemberId(memberId);
            scheduleRepository.deleteByMemberId(memberId);
            memberRoleRepository.deleteByMemberId(memberId);
            memberRepository.delete(member);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return WithdrawResponseDto.success();
    }
}
