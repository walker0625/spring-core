package com.jon.springcore.member;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {

    private Long id;
    private String name;
    private Grade grade;

}
