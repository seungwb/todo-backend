package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.object.ScheduleListItem;
import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.PostScheduleResponseDto;
import kr.co.tododeungjang.web.domain.entity.ScheduleEntity;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.domain.entity.ScheduleListViewEntity;
import kr.co.tododeungjang.web.repository.ScheduleListViewRepository;
import kr.co.tododeungjang.web.repository.ScheduleRepository;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.service.ScheduleService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleListViewRepository scheduleListViewRepository;
    @Override
    public ResponseEntity<? super PostScheduleResponseDto> saveSchedule(PostScheduleRequestDto dto, String email) {
        try {
            MemberEntity member = memberRepository.findByEmail(email);
            Long memberId = member.getId();
            LocalDateTime regDate = LocalDateTime.now();

            ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .location(dto.getLocation())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .memberId(memberId)
                    .regDate(regDate)
                    .build();

            scheduleRepository.save(scheduleEntity);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }



        return PostScheduleResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetScheduleResponseDto> getSchedule(String email) {

        try {
            MemberEntity member = memberRepository.findByEmail(email);

            List<ScheduleListViewEntity> lists = scheduleListViewRepository.findAllByMemberId(member.getId());

            List<ScheduleListItem> scheduleListItems = lists.stream()
                    .map(list -> new ScheduleListItem(
                            list.getName()
                            ,list.getTitle()
                            ,list.getContent()
                            ,list.getStartDate()
                            ,list.getEndDate()
                            ,list.getLocation()
                            ,list.getRegDate()

                    )).toList();
            return GetScheduleResponseDto.success(scheduleListItems);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

}
