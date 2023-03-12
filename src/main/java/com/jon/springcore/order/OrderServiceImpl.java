package com.jon.springcore.order;

import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
// @NoArgsConstructor - 기본 생성자 생성(final인 초기화 필요 필드가 있으면 컴파일 에러 발생으로 사용 불가)
// @AllArgsConstructor - 전체 모든 필드를 가지는 생성자 생성
@RequiredArgsConstructor // final이 있는 필드를 가지고 생성자를 생성(의존 관계가 추가되어도 생성자를 수정할 필요가 없으므로 편리)
public class OrderServiceImpl implements OrderService{

    // final 키워드의 장점 : 생성자에서 실수로 값을 초기화해주지 않으면, 미리 컴파일 오류가 발생하여 파악이 가능
    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;

    // lombok - @RequiredArgsConstructor으로 생략
    // @Autowired 생성자가 하나인 경우 생략 가능
    /*public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountedPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountedPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}

