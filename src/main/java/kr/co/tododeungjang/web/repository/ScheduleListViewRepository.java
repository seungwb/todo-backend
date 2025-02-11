package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.ScheduleListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleListViewRepository extends JpaRepository<ScheduleListViewEntity, Long> {
    List<ScheduleListViewEntity> findAllByMemberId(Long memberId);
}
