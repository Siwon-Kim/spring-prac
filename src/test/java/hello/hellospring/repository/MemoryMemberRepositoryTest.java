package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // static import

// 테스트 시 메서드의 순서는 보장할 수 없다 => 의존 데이터없이 설계해줘야 한다 => @AfterEach 필요
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드 하나에 대한 테스트가 끝날 때마다 저장소나 공용데이터 비워주기
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Siwon");

        repository.save(member);

        // validation
        Member result = repository.findById(member.getId()).get(); // get() => Optional이라
        // Assertions.assertEquals(result, member); - assert option 1 (import org.junit.jupiter.api.Assertions)
        assertThat(member).isEqualTo(result); // - assert option 2
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
