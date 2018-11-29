package com.choish.mjq.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속된 Entity들의 필드(createdDate, modifiedDate)를 컬럼으로 인식하도록
@EntityListeners(AuditingEntityListener.class) // 이 클래스에 Auditing 기능 추가
public class BaseTimeEntity {

    @Column(name = "createdDate", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modifiedDate")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
