package com.jon.springcore.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("create bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("create bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close(); // @PreDestroy가 동작하지 않음(수동 동작)
        System.out.println("call destroy");
        bean1.destroy();
    }

    // @Component 없어도 AnnotationConfigApplicationContext()의 파라미터가 되면 Bean 등록됨
    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        // prototype은 동작하지 않음
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }


    }
}
