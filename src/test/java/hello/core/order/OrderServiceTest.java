package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "member1", Grade.VIP);

        //when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "item1", 10000);

        //then
        assertEquals(1000, order.getDiscountPrice());
    }

}