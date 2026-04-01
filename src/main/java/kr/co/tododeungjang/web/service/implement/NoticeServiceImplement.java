package kr.co.tododeungjang.web.service.implement;

import kr.co.tododeungjang.web.domain.dto.object.NoticeListItem;
import kr.co.tododeungjang.web.domain.dto.request.notice.PostNoticeRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.notice.UpdateNoticeRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.ResponseDto;
import kr.co.tododeungjang.web.domain.dto.response.notice.*;
import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import kr.co.tododeungjang.web.domain.entity.NoticeEntity;
import kr.co.tododeungjang.web.repository.MemberRepository;
import kr.co.tododeungjang.web.repository.NoticeRepository;
import kr.co.tododeungjang.web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplement implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList() {
        List<NoticeListItem> noticeList;
        try {
            List<NoticeEntity> entities = noticeRepository.findAllByOrderByRegDateDesc();
            noticeList = entities.stream().map(NoticeListItem::new).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNoticeListResponseDto.success(noticeList);
    }

    @Override
    @Transactional
    public ResponseEntity<? super GetNoticeResponseDto> getNotice(Long id) {
        NoticeEntity noticeEntity;
        try {
            Optional<NoticeEntity> optional = noticeRepository.findById(id);
            if (optional.isEmpty()) return ResponseDto.notExistedNotice();
            noticeEntity = optional.get();
            noticeEntity.incrementViewCount();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNoticeResponseDto.success(noticeEntity);
    }

    @Override
    public ResponseEntity<? super PostNoticeResponseDto> postNotice(PostNoticeRequestDto dto, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            NoticeEntity noticeEntity = NoticeEntity.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .regDate(LocalDateTime.now())
                    .memberId(member.getId())
                    .viewCount(0L)
                    .build();

            noticeRepository.save(noticeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostNoticeResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super UpdateNoticeResponseDto> updateNotice(Long id, UpdateNoticeRequestDto dto, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            Optional<NoticeEntity> optional = noticeRepository.findById(id);
            if (optional.isEmpty()) return ResponseDto.notExistedNotice();

            NoticeEntity noticeEntity = optional.get();
            if (!noticeEntity.getMemberId().equals(member.getId())) {
                return ResponseDto.validationFailed();
            }

            noticeEntity.update(dto.getTitle(), dto.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UpdateNoticeResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteNoticeResponseDto> deleteNotice(Long id, String email) {
        try {
            if (email == null) return ResponseDto.validationFailed();
            MemberEntity member = memberRepository.findByEmail(email);
            if (member == null) return ResponseDto.notExistedUser();

            Optional<NoticeEntity> optional = noticeRepository.findById(id);
            if (optional.isEmpty()) return ResponseDto.notExistedNotice();

            if (!optional.get().getMemberId().equals(member.getId())) {
                return ResponseDto.validationFailed();
            }

            noticeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteNoticeResponseDto.success();
    }
}
