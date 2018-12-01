package com.choish.mjq.domain.emails;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자의 접근 권한을 protected로 제한 즉, protected Posts() {}와 같은 효과
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@Setter // Setter 자동 생성
@Entity // 테이블과 링크될 클래스임을 나타냄, _으로 이름을 매칭
public class Emails {

    @Id
    @Column(length = 100, nullable = false)
    private String email;

    @Builder // 해당 클래스의 빌더패턴 클래스 생성
    // 실제 DB의 테이블과 매칭되는 클래스로서, Entity 클래스라고 함
    public Emails(String email){
        this.email = email;
    }
}
