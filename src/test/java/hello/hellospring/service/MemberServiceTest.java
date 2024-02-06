package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // DI - repository를 service 생성자에 직접 주입
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // DI - Dependency Injection 생성자 주입
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 성공 케이스
    @Test
    void signUp() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long savedId = memberService.signUp(member);

        // then
        Member foundMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    // 예외케이스
    @Test
    void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.signUp(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.signUp(member2));// 해당 에러 메서드가 실행되어야 성공
        // assertThrows(NullPointerException.class, () -> memberService.signUp(member2)); -> 실패

//        try {
//            memberService.signUp(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}