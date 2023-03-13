package com.jon.springcore.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
// request마다 각기 다른 Bean이 생성됨
// 컨테이너를 띄우는 시점에는 request가 없으므로 생성할 수가 없음(1. Provider를 활용 2.Proxy 사용)
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // request가 없어도 가짜 Bean을 미리 생성해서 넣어둘 수 있음
public class MyLogger {

    private String uuid;
    private String requestUrl;

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope create : " + this);
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("["+uuid+"]"+"["+requestUrl+"]"+message);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+uuid+"] request scope close : " + this);
    }

}