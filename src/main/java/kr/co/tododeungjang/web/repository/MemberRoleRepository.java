package kr.co.tododeungjang.web.repository;

import kr.co.tododeungjang.web.domain.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

    MemberRole findByMemberId(Long id);
}
