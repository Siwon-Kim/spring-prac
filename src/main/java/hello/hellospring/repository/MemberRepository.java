package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // 없는 경우 null 반환 대신 Optional
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
