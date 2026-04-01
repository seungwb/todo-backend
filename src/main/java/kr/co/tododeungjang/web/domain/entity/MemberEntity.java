package kr.co.tododeungjang.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @Setter
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime joinDate;

    public void update(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
