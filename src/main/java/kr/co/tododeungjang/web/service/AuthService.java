package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.auth.SignInRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignUpRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
