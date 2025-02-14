package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.object.ScheduleListItem;
import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.schedule.UpdateScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.GetTodayScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.GetWeeklyScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.DeleteScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.GetScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.PostScheduleResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.UpdateScheduleResponseDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleListViewRepository scheduleListViewRepository;
    @Override
    public ResponseEntity<? super PostScheduleResponseDto> saveSchedule(PostScheduleRequestDto dto, String email) {
        try {
            if(email == null){
                return ResponseDto.validationFailed();
            }
            MemberEntity member = memberRepository.findByEmail(email);
            Long memberId = member.getId();
            OffsetDateTime regDate = OffsetDateTime.now();

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
        List<ScheduleListItem> scheduleListItems;
        try {
            if(email == null){
                return ResponseDto.validationFailed();
            }
            MemberEntity member = memberRepository.findByEmail(email);

            List<ScheduleListViewEntity> lists = scheduleListViewRepository.findAllByMemberIdOrderByStartDateAsc(member.getId());

            scheduleListItems = lists.stream()
                    .map(list -> new ScheduleListItem(
                            list.getId()
                            ,list.getName()
                            ,list.getTitle()
                            ,list.getContent()
                            ,list.getStartDate()
                            ,list.getEndDate()
                            ,list.getLocation()
                            ,list.getRegDate()

                    )).toList();

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetScheduleResponseDto.success(scheduleListItems);
    }

    @Override
    public ResponseEntity<? super DeleteScheduleResponseDto> deleteSchedule(Long id, String email) {
        try {
            if(email == null){
                return ResponseDto.validationFailed();
            }
            scheduleRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteScheduleResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super UpdateScheduleResponseDto> updateSchedule(Long id, String email, UpdateScheduleRequestDto requestBody) {

        try{
            if(email == null){
                return ResponseDto.validationFailed();
            }
            ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow();
            schedule.update(requestBody);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return UpdateScheduleResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetTodayScheduleResponseDto> getTodaySchedule(String today, String email) {
        List<ScheduleListItem> todayScheduleListItems;
        try {
            if(email == null){
                return ResponseDto.validationFailed();
            }
            MemberEntity member = memberRepository.findByEmail(email);
            OffsetDateTime dateTime = null;
            LocalDate localDate = LocalDate.parse(today);
            dateTime = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);

            List<ScheduleListViewEntity> lists = scheduleListViewRepository.
                    findTodayScheduleByDate(member.getId(), dateTime);

            todayScheduleListItems = lists.stream()
                    .map(list -> new ScheduleListItem(
                            list.getId()
                            ,list.getName()
                            ,list.getTitle()
                            ,list.getContent()
                            ,list.getStartDate()
                            ,list.getEndDate()
                            ,list.getLocation()
                            ,list.getRegDate()

                    )).toList();

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTodayScheduleResponseDto.success(todayScheduleListItems);
    }

    @Override
    public ResponseEntity<? super GetWeeklyScheduleResponseDto> getWeeklySchedule(String start, String end, String email) {
        List<ScheduleListItem> weeklyScheduleListItems;
        try {
            if(email == null){
                return ResponseDto.validationFailed();
            }
            MemberEntity member = memberRepository.findByEmail(email);
            OffsetDateTime startDateTime = null;
            OffsetDateTime endDateTime = null;
            LocalDate localDate = LocalDate.parse(start);
            startDateTime = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
            localDate = LocalDate.parse(end);
            endDateTime = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);

            List<ScheduleListViewEntity> lists = scheduleListViewRepository
                    .findWeeklyScheduleByDate(member.getId(), startDateTime, endDateTime);

            weeklyScheduleListItems = lists.stream()
                    .map(list -> new ScheduleListItem(
                            list.getId()
                            ,list.getName()
                            ,list.getTitle()
                            ,list.getContent()
                            ,list.getStartDate()
                            ,list.getEndDate()
                            ,list.getLocation()
                            ,list.getRegDate()

                    )).toList();

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetWeeklyScheduleResponseDto.success(weeklyScheduleListItems);
    }

}
