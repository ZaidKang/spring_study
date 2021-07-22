package SpringStudy.TheCore.discount;

import SpringStudy.TheCore.member.Member;

public interface DiscountPolicy {
    //return 할인 금액이 얼마인지
    int discount(Member member, int price);
}
