package com.jon.springcore.singleton;

import com.jon.springcore.AppConfig;
import com.jon.springcore.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void pureDiContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        MemberService memberService1 = appConfig.memberService();

        // ==
        assertThat(memberService).isNotSameAs(memberService1);
    }

    @Test
    void singletonCallTest() {
        //SingletonService s = new SingletonService(); // private 생성자로 복수의 객체를 막음
        SingletonService instance = SingletonService.getInstance();
        instance.call();
    }

    @Test
    void singletonValueTest() {
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        // equal to
        assertThat(instance).isSameAs(instance2);
    }

    @Test
    void springContainerSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // ==
        assertThat(memberService).isSameAs(memberService1);
    }

}