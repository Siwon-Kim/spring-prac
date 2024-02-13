package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// business logic을 다루는 service class
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // spring bean에 등록되어 있는 repository를 생성자에 넣어준다 => DI: service - repository 연결
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 외부에서 리파지토리를 주입할 수 있도록 생성자 설정
    }

    // 회원가입
    public Long signUp(Member member) {
        validateDuplicateMember(member); // 중복 회원 체크
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); // Optional로 멤버 객체를 감싸고 있기 때문에 if문 대신 다양한 메서드를 사용할 수 있음
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
