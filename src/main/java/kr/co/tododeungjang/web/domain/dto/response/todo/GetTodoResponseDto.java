package kr.co.tododeungjang.web.domain.dto.response.todo;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.object.TodoListItem;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetTodoResponseDto extends ResponseDto {

    private final List<TodoListItem> todoListItems;

    public GetTodoResponseDto(List<TodoListItem> todoListItems) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.todoListItems = todoListItems;
    }

    public static ResponseEntity<GetTodoResponseDto> success(List<TodoListItem> todoListItems){
        GetTodoResponseDto result = new GetTodoResponseDto(todoListItems);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
