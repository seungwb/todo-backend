package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.member.ChangePasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.member.UpdateMemberRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.member.WithdrawRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.member.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity<? super GetMemberResponseDto> getMe(String email);
    ResponseEntity<? super UpdateMemberResponseDto> updateMe(UpdateMemberRequestDto dto, String email);
    ResponseEntity<? super ChangePasswordResponseDto> changePassword(ChangePasswordRequestDto dto, String email);
    ResponseEntity<? super WithdrawResponseDto> withdraw(WithdrawRequestDto dto, String email);
}
