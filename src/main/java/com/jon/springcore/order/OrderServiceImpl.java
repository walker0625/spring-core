package com.jon.springcore.order;

import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.discount.FixDiscountPolicy;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberRepository;
import com.jon.springcore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountedPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountedPrice);
    }
}
