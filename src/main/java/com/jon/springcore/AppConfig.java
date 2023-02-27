package com.jon.springcore;

import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.discount.RateDiscountPolicy;
import com.jon.springcore.member.MemberRepository;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;
import com.jon.springcore.member.MemoryMemberRepository;
import com.jon.springcore.order.OrderService;
import com.jon.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
