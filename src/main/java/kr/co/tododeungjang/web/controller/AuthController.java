package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.auth.FindIdRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignInRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.auth.SignUpRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.FindIdResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignUpResponseDto;
import kr.co.tododeungjang.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody) {
        return authService.signUp(requestBody);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody){
        return authService.signIn(requestBody);
    }

    @PostMapping("/find-id")
    public ResponseEntity<? super FindIdResponseDto> findId(
            @RequestBody @Valid FindIdRequestDto requestBody
            ){
        return authService.findId(requestBody);
    }
}
