package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {


    List<TodoEntity> findByMemberId(Long memberId);
}
