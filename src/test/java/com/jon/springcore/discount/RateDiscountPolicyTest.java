package com.jon.springcore.discount;

import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 되어야 한다")
    void vipTest() {
        // given
        Member vipmember = new Member(1L, "VIPMEMBER", Grade.VIP);
        // when
        int discountPrice = discountPolicy.discount(vipmember, 20000);
        // then
        Assertions.assertThat(discountPrice).isEqualTo(2000);
    }

    @Test
    @DisplayName("vip가 아니면 10프로 할인이 되지 않아야 한다.")
    void notVipTest() {
        // given
        Member basicMember = new Member(1L, "BASICMEMBER", Grade.BASIC);
        // when
        int discountPrice = discountPolicy.discount(basicMember, 20000);
        // then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

}