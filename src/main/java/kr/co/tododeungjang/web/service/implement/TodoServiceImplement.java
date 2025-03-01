package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.object.TodoListItem;
import kr.co.tododeungjang.web.domain.dto.request.todo.PostTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateStateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.todo.UpdateTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.*;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.domain.entity.TodoEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.repository.TodoRepository;
import kr.co.tododeungjang.web.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImplement implements TodoService {

    private final TodoRepository todoRepository;

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<? super PostTodoResponseDto> saveTodo(PostTodoRequestDto dto, String email) {
        try{
            if(email == null) return ResponseDto.validationFailed();

            MemberEntity member = memberRepository.findByEmail(email);

            if(member == null) return PostTodoResponseDto.notExistedUser();

            Long memberId = member.getId();
            OffsetDateTime regDate = OffsetDateTime.now();
            TodoEntity todoEntity = TodoEntity.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .state(true)
                    .regDate(regDate)
                    .memberId(memberId)
                    .build();

            todoRepository.save(todoEntity);
        }catch (Exception e){
            return ResponseDto.databaseError();
        }

        return PostTodoResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetTodoResponseDto> getTodo(String email) {
        List<TodoListItem> todoListItems;
        try{
            if(email == null) return ResponseDto.validationFailed();

            MemberEntity member = memberRepository.findByEmail(email);

            if(member == null) return GetTodoResponseDto.notExistedUser();
            List<TodoEntity> lists = todoRepository.findByMemberId(member.getId());
            todoListItems = lists.stream().map(
                    list-> new TodoListItem(
                            list.getId()
                            ,list.getTitle()
                            ,list.getContent()
                            ,list.getRegDate()
                            ,list.getState()
                    )
            ).toList();

        } catch (Exception e){
            return ResponseDto.databaseError();
        }
        return GetTodoResponseDto.success(todoListItems);
    }

    @Override
    @Transactional
    public ResponseEntity<? super UpdateStateTodoResponseDto> updateState(Long id, UpdateStateTodoRequestDto requestBody, String email) {
        try {
            if(email == null) return ResponseDto.validationFailed();

            MemberEntity member = memberRepository.findByEmail(email);

            if(member == null) return UpdateStateTodoResponseDto.notExistedUser();

            Optional<TodoEntity> optionalTodo = todoRepository.findById(id);
            if (optionalTodo.isEmpty()) return UpdateStateTodoResponseDto.notExistedTodo();

            TodoEntity todo = optionalTodo.get();
            todo.updateState(requestBody);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UpdateStateTodoResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super UpdateTodoResponseDto> update(Long id, UpdateTodoRequestDto requestBody, String email) {
        try {
            if(email == null) return ResponseDto.validationFailed();

            MemberEntity member = memberRepository.findByEmail(email);

            if(member == null) return UpdateTodoResponseDto.notExistedUser();

            Optional<TodoEntity> optionalTodo = todoRepository.findById(id);
            if (optionalTodo.isEmpty()) return UpdateTodoResponseDto.notExistedTodo();

            TodoEntity todo = optionalTodo.get();
            todo.update(requestBody);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UpdateTodoResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteTodoResponseDto> delete(Long id, String email) {
        try {
            if(email == null) return ResponseDto.validationFailed();

            MemberEntity member = memberRepository.findByEmail(email);

            if(member == null) return DeleteTodoResponseDto.notExistedUser();

            Optional<TodoEntity> optionalTodo = todoRepository.findById(id);
            if (optionalTodo.isEmpty()) return DeleteTodoResponseDto.notExistedTodo();

            todoRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteTodoResponseDto.success();
    }
}
