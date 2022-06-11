package hello.core.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "member1", Grade.BASIC);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertEquals(member, findMember);

    }

}