package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.object.ScheduleListItem;
import kr.co.tododeungjang.web.domain.dto.request.schedule.PostScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.schedule.UpdateScheduleRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.schedule.*;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.domain.entity.ScheduleEntity;
import kr.co.tododeungjang.web.domain.entity.ScheduleListViewEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.repository.ScheduleListViewRepository;
import kr.co.tododeungjang.web.repository.ScheduleRepository;
import kr.co.tododeungjang.web.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleListViewRepository scheduleListViewRepository;

    private MemberEntity findMemberByEmail(String email) {
        if (email == null) return null;
        return memberRepository.findByEmail(email);
    }

    private List<ScheduleListItem> toScheduleListItems(List<ScheduleListViewEntity> lists) {
        return lists.stream().map(list -> new ScheduleListItem(
                list.getId(),
                list.getName(),
                list.getTitle(),
                list.getContent(),
                list.getStartDate(),
                list.getEndDate(),
                list.getLocation(),
                list.getRegDate()
        )).toList();
    }

    @Override
    public ResponseEntity<? super PostScheduleResponseDto> saveSchedule(PostScheduleRequestDto dto, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = findMemberByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .location(dto.getLocation())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .memberId(member.getId())
                    .regDate(OffsetDateTime.now())
                    .build();

            scheduleRepository.save(scheduleEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostScheduleResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetScheduleResponseDto> getSchedule(String email) {
        List<ScheduleListItem> scheduleListItems;
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = findMemberByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            List<ScheduleListViewEntity> lists = scheduleListViewRepository
                    .findAllByMemberIdOrderByStartDateAsc(member.getId());
            scheduleListItems = toScheduleListItems(lists);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetScheduleResponseDto.success(scheduleListItems);
    }

    @Override
    public ResponseEntity<? super DeleteScheduleResponseDto> deleteSchedule(Long id, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = findMemberByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            Optional<ScheduleEntity> optionalSchedule = scheduleRepository.findById(id);
            if (optionalSchedule.isEmpty()) return ResponseDto.notExistedSchedule();

            scheduleRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteScheduleResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super UpdateScheduleResponseDto> updateSchedule(Long id, String email, UpdateScheduleRequestDto requestBody) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = findMemberByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            Optional<ScheduleEntity> optionalSchedule = scheduleRepository.findById(id);
            if (optionalSchedule.isEmpty()) return ResponseDto.notExistedSchedule();

            ScheduleEntity schedule = optionalSchedule.get();
            schedule.update(
                    requestBody.getTitle(),
                    requestBody.getContent(),
                    requestBody.getLocation(),
                    requestBody.getStartDate(),
                    requestBody.getEndDate()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return UpdateScheduleResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetTodayScheduleResponseDto> getTodaySchedule(String today, String email) {
        List<ScheduleListItem> todayScheduleListItems;
        try {
            if (email == null) return ResponseDto.validationFailed();
            if (today == null) return ResponseDto.validationFailed();

            MemberEntity member = findMemberByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            LocalDate localDate = LocalDate.parse(today);
            OffsetDateTime dateTime = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);

            List<ScheduleListViewEntity> lists = scheduleListViewRepository
                    .findTodayScheduleByDate(member.getId(), dateTime);
            todayScheduleListItems = toScheduleListItems(lists);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTodayScheduleResponseDto.success(todayScheduleListItems);
    }

    @Override
    public ResponseEntity<? super GetWeeklyScheduleResponseDto> getWeeklySchedule(String start, String end, String email) {
        List<ScheduleListItem> weeklyScheduleListItems;
        try {
            if (email == null) return ResponseDto.validationFailed();
            if (start == null || end == null) return ResponseDto.validationFailed();

            MemberEntity member = findMemberByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            OffsetDateTime startDateTime = LocalDate.parse(start).atStartOfDay().atOffset(ZoneOffset.UTC);
            OffsetDateTime endDateTime = LocalDate.parse(end).atStartOfDay().atOffset(ZoneOffset.UTC);

            List<ScheduleListViewEntity> lists = scheduleListViewRepository
                    .findWeeklyScheduleByDate(member.getId(), startDateTime, endDateTime);
            weeklyScheduleListItems = toScheduleListItems(lists);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetWeeklyScheduleResponseDto.success(weeklyScheduleListItems);
    }
}
