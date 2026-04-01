package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.auth.FindIdRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.ResetPasswordRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignInRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignUpRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.FindIdResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.ResetPasswordResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super FindIdResponseDto> findId(FindIdRequestDto dto);
    ResponseEntity<? super ResetPasswordResponseDto> resetPassword(ResetPasswordRequestDto dto);
}
