package com.jon.springcore.order;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OrderServiceImplTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(com.jon.springcore.AppConfig.class);
        memberService = ac.getBean("memberService", MemberService.class);
        orderService = ac.getBean("orderService", OrderService.class);

        //memberService = new MemberServiceImpl(new MemoryMemberRepository());
        //orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "B", Grade.VIP);

        memberService.join(member);
        Order order = orderService.createOrder(memberId, "Bitem", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}