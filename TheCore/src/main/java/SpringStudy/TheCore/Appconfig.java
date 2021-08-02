package SpringStudy.TheCore;

import SpringStudy.TheCore.discount.DiscountPolicy;
import SpringStudy.TheCore.discount.FixDiscountPolicy;
import SpringStudy.TheCore.member.MemberRepository;
import SpringStudy.TheCore.member.MemberService;
import SpringStudy.TheCore.member.MemberServiceImpl;
import SpringStudy.TheCore.member.MemoryMemberRepository;
import SpringStudy.TheCore.order.OrderService;
import SpringStudy.TheCore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
//    }

    //역할과 구현을 정확하게 명시하기 위해 리팩토링
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    //설계가 한 눈에 들어옴.

}
