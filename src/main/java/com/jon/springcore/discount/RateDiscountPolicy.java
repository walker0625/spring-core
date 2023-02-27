package com.jon.springcore.discount;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

    }
}
