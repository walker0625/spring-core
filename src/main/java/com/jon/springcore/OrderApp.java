package com.jon.springcore;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;
import com.jon.springcore.order.Order;
import com.jon.springcore.order.OrderService;
import com.jon.springcore.order.OrderServiceImpl;

import java.util.Arrays;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        
        Long memberId = 1L;
        Member member = new Member(memberId, "A", Grade.VIP);
        memberService.join(member);
        
        Order order = orderService.createOrder(memberId,"item", 10000);
        System.out.println("order = " + order);
        System.out.println(order.calculatePrice());
    }

}
