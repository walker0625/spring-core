package com.jon.springcore.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 url : " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : "+ url + " message : " + message);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    // 의존관계 주입(초기화) 후에 동작
    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    // ac.close 이후
    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }

}
