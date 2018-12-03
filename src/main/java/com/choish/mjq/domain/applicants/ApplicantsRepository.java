package com.choish.mjq.domain.applicants;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantsRepository extends JpaRepository<Applicants, Long> {
    Iterable<Applicants> findAllByPostid(Long postid);
}
