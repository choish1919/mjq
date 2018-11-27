package com.choish.mjq.domain.users;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String pw;

    @Column(length = 24, nullable = false)
    private String nickname;

    private Long exp;
    private Long lv;

    @Column(length = 41, unique = true, nullable = false)
    private String email;

    private String achievements;

    @Builder // 해당 클래스의 빌더패턴 클래스 생성
    // 실제 DB의 테이블과 매칭되는 클래스로서, Entity 클래스라고 함
    public Users(String email, String pw, String nickname, Long exp, Long lv, String achievements){
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.exp = exp;
        this.lv = lv;
    }
}