
package com.jon.springcore.findbean;

import com.jon.springcore.AppConfig;
import com.jon.springcore.discount.DiscountPolicy;
import com.jon.springcore.member.MemberRepository;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;
import com.jon.springcore.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FindBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = " + bean);
        }
    }

    @Test
    void findAllApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =  ac.getBeanDefinition(beanDefinitionName);
            
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("ac.getBean(beanDefinitionName) = " + ac.getBean(beanDefinitionName));
            }
            
        }
    }

    @Test
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("구현체로 비교")
    void findBeanByInstance() {
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    void findBeanResultNull() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("no", MemberService.class));
    }

    //////////////////////////////////

    AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    void duplicateBean() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac2.getBean(MemberRepository.class));
    }

    @Test
    void duplicateBean2() {
        Assertions.assertThat(ac2.getBean("memberRepository2", MemberRepository.class))
                  .isInstanceOf(MemberRepository.class);
    }

    @Test
    void duplicationBean3() {
        Map<String, MemberRepository> repoMap  = ac2.getBeansOfType(MemberRepository.class);
        for(String key : repoMap.keySet()) {
            System.out.println("key = " + repoMap.get(key));
        }
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }


}

