package com.jon.springcore.findbean;

import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.discount.FixDiscountPolicy;
import com.jon.springcore.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ParentBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ParentBeans.class);

    @Test
    void getParentBean() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    void getSpecificBean() {
        org.assertj.core.api.Assertions.assertThat(ac.getBean("rateDiscountPolicy", DiscountPolicy.class))
                                       .isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    void getAllBean() {
        Map<String, Object> allBeans = ac.getBeansOfType(Object.class);
        for(String key : allBeans.keySet()) {
            System.out.println("bean = " + allBeans.get(key).toString());
        }
    }

    @Configuration
    static class ParentBeans {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }

}
