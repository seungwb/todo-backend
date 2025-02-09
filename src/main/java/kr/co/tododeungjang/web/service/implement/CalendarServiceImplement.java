package kr.co.tododeungjang.web.service.implement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kr.co.tododeungjang.web.domain.dto.request.auth.ScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.auth.SignInResponseDto;
import kr.co.tododeungjang.web.domain.entity.CalendarEntity;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.repository.CalendarRepository;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CalendarServiceImplement implements CalendarService {

    private final MemberRepository memberRepository;
    private final CalendarRepository calendarRepository;
    @Override
    public ResponseEntity<? super SignInResponseDto> schedule(ScheduleRequestDto dto, String email) {

        MemberEntity member = memberRepository.findByEmail(email);
        Long memberId = member.getId();
        LocalDateTime regDate = LocalDateTime.now();

        CalendarEntity calendarEntity = CalendarEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .memberId(memberId)
                .regDate(regDate)
                .build();

        calendarRepository.save(calendarEntity);


        return null;
    }
}
