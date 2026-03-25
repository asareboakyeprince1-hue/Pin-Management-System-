package com.itconsortium.creditunion.chango.repository;

import com.itconsortium.creditunion.chango.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
