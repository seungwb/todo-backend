package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
