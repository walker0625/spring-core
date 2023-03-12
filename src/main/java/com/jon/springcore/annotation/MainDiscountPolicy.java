package com.jon.springcore.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy") //Qualifier를 어노테이션으로 쓰면 오타를 방지할 수 있음(빈/사용처에 달아주면 됨)
public @interface MainDiscountPolicy {
}
