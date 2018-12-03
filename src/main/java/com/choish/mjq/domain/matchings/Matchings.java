package com.choish.mjq.domain.matchings;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자의 접근 권한을 protected로 제한 즉, protected Posts() {}와 같은 효과
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@Setter // Setter 자동 생성
@Entity // 테이블과 링크될 클래스임을 나타냄, _으로 이름을 매칭
@IdClass(Matchings.class)
public class Matchings implements Serializable {

    @Id // 해당 테이블의 PK 필드를 나타냄
    // 팀장 번호
    private Long authorid;

    @Id // 해당 테이블의 PK 필드를 나타냄
    // 팀원 번호
    private Long applicantsid;

    // 게시물 번호
    private Long postid;

    @Builder // 해당 클래스의 빌더패턴 클래스 생성
    // 실제 DB의 테이블과 매칭되는 클래스로서, Entity 클래스라고 함
    public Matchings(Long authorid, Long applicantsid, Long postid){
        this.authorid = authorid;
        this.applicantsid = applicantsid;
        this.postid = postid;
    }
}