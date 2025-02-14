package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.ScheduleListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ScheduleListViewRepository extends JpaRepository<ScheduleListViewEntity, Long> {
    List<ScheduleListViewEntity> findAllByMemberIdOrderByStartDateAsc(Long memberId);

    @Query(value = "SELECT * FROM schedule_list_view slv " +
            "WHERE slv.member_id = :memberId " +
            "AND slv.start_date::DATE <= :today " +
            "AND slv.end_date::DATE >= :today " +
            "ORDER BY slv.start_date",
            nativeQuery = true)
    List<ScheduleListViewEntity> findTodayScheduleByDate(@Param("memberId") Long memberId,
                                                    @Param("today") OffsetDateTime today);

    @Query(value = "SELECT * FROM schedule_list_view slv " +
            "WHERE slv.member_id = :memberId " +
            "AND slv.start_date::DATE <= :end " +
            "AND slv.end_date::DATE >= :start " +
            "ORDER BY slv.start_date",
            nativeQuery = true)
    List<ScheduleListViewEntity> findWeeklyScheduleByDate(@Param("memberId") Long memberId,
                                                          @Param("start") OffsetDateTime startDateTime
                                                            ,@Param("end") OffsetDateTime endDateTime);
}
