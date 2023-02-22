package com.jon.springcore.order;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "B", Grade.VIP);

        memberService.join(member);
        Order order = orderService.createOrder(memberId, "Bitem", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}