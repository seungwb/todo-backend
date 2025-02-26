package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.todo.PostTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateStateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.GetTodoResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.PostTodoResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.UpdateStateTodoResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.UpdateTodoResponseDto;
import org.springframework.http.ResponseEntity;

public interface TodoService {

    ResponseEntity<? super PostTodoResponseDto> saveTodo(PostTodoRequestDto dto, String email);

    ResponseEntity<? super GetTodoResponseDto> getTodo(String email);

    ResponseEntity<? super UpdateStateTodoResponseDto> updateState(Long id,UpdateStateTodoRequestDto dto, String email);

    ResponseEntity<? super UpdateTodoResponseDto> update(Long id, UpdateTodoRequestDto dto, String email);
}
