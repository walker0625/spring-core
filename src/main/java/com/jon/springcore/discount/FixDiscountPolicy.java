package com.jon.springcore.discount;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary // main db 커넥션에 달아주는 방식 활용
//@Qualifier("mainDiscountPolicy") - 1. 사용처에도 적어줘야함 2. @Primary에 우선함(수동이므로) 3. 빈이름 자체를 바꾸는 것은 아님
@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else{
            return 0;
        }

    }

}
