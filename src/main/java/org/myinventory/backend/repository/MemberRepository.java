package org.myinventory.backend.repository;

import org.myinventory.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByLoginIdAndPassword(String loginId, String password);


}
