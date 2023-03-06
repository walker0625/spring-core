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

@Configuration // singleton으로 관리하게 되는 핵심(없으면 CGLIB이 동작하지 않아 객체를 호출할때마다 생성)
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository"); // 3번 호출되어야 하지만 실제로는 1번만 호출됨(CGLIB으로 변형된 코드로 인함)
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
