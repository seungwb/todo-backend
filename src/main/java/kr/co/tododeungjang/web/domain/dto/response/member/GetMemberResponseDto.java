package kr.co.tododeungjang.web.domain.dto.response.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.tododeungjang.web.common.ResponseCode;
import kr.co.tododeungjang.web.common.ResponseMessage;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetMemberResponseDto extends ResponseDto {

    private final String name;
    private final String email;
    private final String phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime joinDate;

    private GetMemberResponseDto(MemberEntity member) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.joinDate = member.getJoinDate();
    }

    public static ResponseEntity<GetMemberResponseDto> success(MemberEntity member) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetMemberResponseDto(member));
    }
}
