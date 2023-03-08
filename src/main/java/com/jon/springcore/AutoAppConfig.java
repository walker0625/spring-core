package com.jon.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 기본적으로는 @SpringBootApplication 내부에 @ComponentScan이 포함되기 때문에
// boot 프로젝트로 생성하면 AutoAppConfig 같은 클래스를 따로 만들 필요가 없음
@Configuration
@ComponentScan(
        // 기존 예제들의 @Configuration 설정을 제외하기 위함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

        // 아래처럼 따로 설정하지 않으면 @ComponentScan이 달린 class 위치를 root(com.jon.springcore)로 하여 scan 한다
        // basePackageClasses = AutoAppConfig.class,
        // basePackages = "com.jon.springcore.member",
)
public class AutoAppConfig {



}

