package kr.co.tododeungjang.web.service;

import kr.co.tododeungjang.web.domain.dto.request.notice.PostNoticeRequestDto;
import kr.co.tododeungjang.web.domain.dto.request.notice.UpdateNoticeRequestDto;
import kr.co.tododeungjang.web.domain.dto.response.notice.*;
import org.springframework.http.ResponseEntity;

public interface NoticeService {

    ResponseEntity<? super GetNoticeListResponseDto> getNoticeList();
    ResponseEntity<? super GetNoticeResponseDto> getNotice(Long id);
    ResponseEntity<? super PostNoticeResponseDto> postNotice(PostNoticeRequestDto dto, String email);
    ResponseEntity<? super UpdateNoticeResponseDto> updateNotice(Long id, UpdateNoticeRequestDto dto, String email);
    ResponseEntity<? super DeleteNoticeResponseDto> deleteNotice(Long id, String email);
}
