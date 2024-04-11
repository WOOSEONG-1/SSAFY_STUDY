package hello.HelloSpring.service;

import hello.HelloSpring.Repository.MemoryMemberRepository;
import hello.HelloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;
    @BeforeEach
    public void BeforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void AfterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        //given ( 기반으로 하는 데이터 )
        Member member = new Member();
        member.setName("hello");

        //when ( 주어진 상황 )
        Long saveId = memberService.join(member);

        //then ( 도출할 결과 )
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }

    @Test
    void findOne() {
    }
}