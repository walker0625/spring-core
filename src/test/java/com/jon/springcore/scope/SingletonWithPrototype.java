package com.jon.springcore.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototype {

    @Test
    void prototypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        assertThat(bean.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        assertThat(bean1.logic()).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        assertThat(bean2.logic()).isEqualTo(1); // 의도는 새롭게 생성되는 것이라 1이어야 함
    }

    @Scope("singleton")
    static class ClientBean {
        //private final PrototypeBean prototypeBean; // singleton 생성시점에 주입이 되고 계속 유지됨(prototype 이지만 새롭게 생성되지 않음)


        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider; // java 표준이라 스프링에 의존하기 않음
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider; // prototype을 DL할 수 있게 제공(스프링 컨테이너에서 해당 타입을 꺼내서 제공)
        
        /*@Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/

        public int logic() {
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 새로운 prototype을 가져옴
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return this.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }

}
