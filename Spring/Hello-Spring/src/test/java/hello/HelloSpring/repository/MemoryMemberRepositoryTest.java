package hello.HelloSpring.repository;

import hello.HelloSpring.Repository.MemberRepository;
import hello.HelloSpring.Repository.MemoryMemberRepository;
import hello.HelloSpring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repos = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        repos.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("덕배");

        repos.save(member);

        repos.findbyId(member.getId());

        Member result = repos.findbyId(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repos.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repos.save(member2);

        Member result = repos.findbyName("Spring1").get();


        Assertions.assertEquals(member1, result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repos.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repos.save(member2);

        List<Member> result = repos.findAll();

        Assertions.assertEquals(result.size(), 2);
    }
}
