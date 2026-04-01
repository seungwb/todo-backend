package kr.co.tododeungjang.web.domain.dto.response.member;

import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class WithdrawResponseDto extends ResponseDto {

    private WithdrawResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<WithdrawResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new WithdrawResponseDto());
    }
}
