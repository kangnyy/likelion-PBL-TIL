package com.likelion.PBL.member.repository;

import com.likelion.PBL.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    boolean existsByName(String name);
}
