package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.todo.PostTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.GetTodoResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.PostTodoResponseDto;
import kr.co.tododeungjang.web.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("")
    public ResponseEntity<? super PostTodoResponseDto> createTodo(
            @Valid @RequestBody PostTodoRequestDto requestBody,
            @AuthenticationPrincipal String email
            ){


        return todoService.saveTodo(requestBody, email);
    }

    @GetMapping("")
    public ResponseEntity<? super GetTodoResponseDto> getTodo(
            @AuthenticationPrincipal String email
    ){
        return todoService.getTodo(email);
    }
}
