package com.jon.springcore.singleton;

import com.jon.springcore.AppConfig;
import com.jon.springcore.member.MemberRepository;
import com.jon.springcore.member.MemberService;
import com.jon.springcore.member.MemberServiceImpl;
import com.jon.springcore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberRepository memberRepository0 = ac.getBean("memberRepository", MemberRepository.class);
        System.out.println("memberRepository0 = " + memberRepository0);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        System.out.println("memberRepository1 = " + memberRepository1);

        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberRepository0).isSameAs(memberRepository1);
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }
    
    @Test
    void deepConfig() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean); // EnhancerBySpringCGLIB
    }
}
