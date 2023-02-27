package com.jon.springcore.order;

import com.jon.springcore.AppConfig;
import com.jon.springcore.discount.FixDiscountPolicy;
import com.jon.springcore.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
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