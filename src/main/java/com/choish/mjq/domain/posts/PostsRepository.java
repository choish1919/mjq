package com.choish.mjq.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

// DB Layer 접근자로 <Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메소드 자동 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByOrderByIdDesc();
    //@Query("SELECT p " + "FROM Posts p " + "ORDER BY p.id DESC")
    //Stream<Posts> findAllDesc();
}