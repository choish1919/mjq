package com.choish.mjq.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// DB Layer 접근자로 <Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메소드 자동 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByOrderByIdDesc();
    Page<Posts> findAllByOrderByIdDesc(Pageable pageable);

    //@Query("SELECT p " + "FROM Posts p " + "ORDER BY p.id DESC")
    //Stream<Posts> findAllDesc();
}
