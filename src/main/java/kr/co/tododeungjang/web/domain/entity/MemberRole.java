package kr.co.tododeungjang.web.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {

    private Long id;
    private String roleName;
    private Long memberId;
}
