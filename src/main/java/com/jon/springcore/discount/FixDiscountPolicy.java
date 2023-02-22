package com.jon.springcore.discount;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else{
            return 0;
        }
    }
}
