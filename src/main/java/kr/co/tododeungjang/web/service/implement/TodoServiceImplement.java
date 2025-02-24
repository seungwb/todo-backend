package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.request.todo.PostTodoRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.todo.PostTodoResponseDto;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.domain.entity.TodoEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.repository.TodoRepository;
import kr.co.tododeungjang.web.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TodoServiceImplement implements TodoService {

    private final TodoRepository todoRepository;

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<? super PostTodoResponseDto> saveTodo(PostTodoRequestDto dto, String email) {
        try{
            if(email == null){
                return ResponseDto.validationFailed();
            }
            MemberEntity member = memberRepository.findByEmail(email);
            Long memberid = member.getId();
            OffsetDateTime regDate = OffsetDateTime.now();
            TodoEntity todoEntity = TodoEntity.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .state(true)
                    .regDate(regDate)
                    .memberId(memberid)
                    .build();

            todoRepository.save(todoEntity);
        }catch (Exception e){
            return ResponseDto.databaseError();
        }

        return PostTodoResponseDto.success();
    }
}
