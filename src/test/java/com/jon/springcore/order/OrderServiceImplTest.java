package com.jon.springcore.order;

import com.jon.springcore.AppConfig;
import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OrderServiceImplTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void createOrder() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "B", Grade.VIP);

        memberService.join(member);
        Order order = orderService.createOrder(memberId, "Bitem", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}