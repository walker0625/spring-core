package com.jon.springcore.order;

import com.jon.springcore.discount.FixDiscountPolicy;
import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import com.jon.springcore.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest2 {

    // 생성자 주입방식으로 만든 객체의 테스트 방식 : 수정자 주입 방식은 선택이 필요하다면 사용하고, 필드 주입은 지양(단위 test에서 내부 객체를 넣어줄 방법이 없음)
    @Test
    void orderTest() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "A", Grade.VIP));

        // 생성자 주입 방식으로 만들기 때문에 컴파일 단계에서 뭘 넣어야 하는지 파악이 가능하고, 필요하다면 위처럼 생성해서 넣어주는 것도 가능하다
        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        Order itemA = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);
    }

}