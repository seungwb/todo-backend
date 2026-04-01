package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    void deleteByMemberId(Long memberId);
}
