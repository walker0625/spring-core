package com.jon.springcore.autowired;

import com.jon.springcore.AutoAppConfig;
import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.member.Grade;
import com.jon.springcore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "a", Grade.VIP);

        int fixDiscount = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(fixDiscount).isEqualTo(1000);

        int rateDiscount = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscount).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            //{fixDiscountPolicy=com.jon.springcore.discount.FixDiscountPolicy@1c3146bc,
            // rateDiscountPolicy=com.jon.springcore.discount.RateDiscountPolicy@56c698e3}
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        // 전략 패턴으로 파라미터(discountCode)에 따라 fix, rate 할인을 구분가능
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
