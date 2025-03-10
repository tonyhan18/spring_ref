package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*테스트 순서가 각자 따로 이기 때문에 전체 실행시 문제가 생긴다
    * 그래서 테스트가 끝나면 Clear 해주어야 한다
    * forEach를 활용해 테스트가 실행하고 끝날때마다 이게 실행된다*/
    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
        assertThat(member).isEqualTo(result);
        //Assertions.assertEquals()
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save((member1));

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save((member2));

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save((member1));

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save((member2));

        List<Member> result =repository.findAll();

        assertThat(result).isEqualTo(3);
    }


}
