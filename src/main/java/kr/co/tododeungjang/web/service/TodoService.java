package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.todo.PostTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.PostTodoResponseDto;
import org.springframework.http.ResponseEntity;

public interface TodoService {

    ResponseEntity<? super PostTodoResponseDto> saveTodo(PostTodoRequestDto dto, String email);
}
