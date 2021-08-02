package SpringStudy.TheCore.order;

import SpringStudy.TheCore.discount.DiscountPolicy;
import SpringStudy.TheCore.discount.FixDiscountPolicy;
import SpringStudy.TheCore.member.Member;
import SpringStudy.TheCore.member.MemberRepository;
import SpringStudy.TheCore.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); ->배우가 직접 다른 배역의 배우 섭외까지 맡고 있는 코드
    //->관심사를 분리하자!
    //공연 기획자가 나올 시점이다.
    //애플리케이션의 전체 동작 방식을 구성하기 위해 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스인 appconfig를 만들자.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    //->RequiredArgsConstructor가 생성자를 알아서 만들어주는 lombok 어노테이션임
    //final이 붙으면 무조건 필수값이니낀 저 final을 가지고 저 생성자를 만들어준다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
