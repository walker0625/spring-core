package com.jon.springcore.order;

import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountedPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountedPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
