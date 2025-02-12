package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.ScheduleListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleListViewRepository extends JpaRepository<ScheduleListViewEntity, Long> {
    List<ScheduleListViewEntity> findAllByMemberIdOrderByStartDateAsc(Long memberId);
}
