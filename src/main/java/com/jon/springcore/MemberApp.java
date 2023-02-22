package com.jon.springcore;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "A", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}
