package SpringStudy.TheCore.order;

import SpringStudy.TheCore.Appconfig;
import SpringStudy.TheCore.member.Grade;
import SpringStudy.TheCore.member.Member;
import SpringStudy.TheCore.member.MemberService;
import SpringStudy.TheCore.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        Appconfig appconfig = new Appconfig();
//        MemberService memberService=appconfig.memberService();
//        OrderService orderService = appconfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "ZaidKang", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
