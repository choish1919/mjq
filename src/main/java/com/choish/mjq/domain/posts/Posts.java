package com.choish.mjq.domain.posts;


import com.choish.mjq.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자의 접근 권한을 protected로 제한 즉, protected Posts() {}와 같은 효과
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@Entity // 테이블과 링크될 클래스임을 나타냄, _으로 이름을 매칭
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더패턴 클래스 생성
    // 실제 DB의 테이블과 매칭되는 클래스로서, Entity 클래스라고 함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
