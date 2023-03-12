package com.jon.springcore.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    // @Autowired : 필드 주입은 비추천, test 코드등에서 순수 자바로 test가 어렵기 때문에(memberRepository 값을 넣어줄 수가 없음)
    private final MemberRepository memberRepository;

    // @Autowired 생성자가 하나인 경우 생략 가능
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}