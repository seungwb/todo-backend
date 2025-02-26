package kr.co.tododeungjang.web.controller;

import jakarta.validation.Valid;
import kr.co.tododeungjang.web.domain.dto.request.todo.PostTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateStateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.*;
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

    @PutMapping("/toggle/{id}")
    public ResponseEntity<? super UpdateStateTodoResponseDto> updateState(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateStateTodoRequestDto requestBody,
            @AuthenticationPrincipal String email
    ){

        return todoService.updateState(id, requestBody, email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? super UpdateTodoResponseDto> update(
            @PathVariable("id") Long id
            , @Valid @RequestBody UpdateTodoRequestDto requestBody
            , @AuthenticationPrincipal String email
            ){

        return todoService.update(id, requestBody, email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? super DeleteTodoResponseDto> delete(
        @PathVariable("id") Long id
        , @AuthenticationPrincipal String email
    ){

        return todoService.delete(id, email);
    }
}
