package SpringStudy.TheCore.order;

import SpringStudy.TheCore.Appconfig;
import SpringStudy.TheCore.member.Grade;
import SpringStudy.TheCore.member.Member;
import SpringStudy.TheCore.member.MemberService;
import SpringStudy.TheCore.member.MemberServiceImpl;
import SpringStudy.TheCore.singleton.SingletonTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        Appconfig appconfig = new Appconfig();
        memberService = appconfig.memberService();
        orderService = appconfig.orderService();
    }


    @Test
    void CreateOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.findMember(memberId);

        Order order = orderService.createOrder(memberId, "ipad", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }
}
