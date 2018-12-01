package com.choish.mjq.domain.emails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailsRepository extends JpaRepository<Emails, String> {
    Emails findByEmail(String email);
}
