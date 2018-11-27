package com.choish.mjq.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

// DB Layer 접근자로 <Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메소드 자동 생성
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndPw(String email, String pw);
    Users findByEmail(String email);
}
