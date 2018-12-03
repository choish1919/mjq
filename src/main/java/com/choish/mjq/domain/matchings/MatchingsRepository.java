package com.choish.mjq.domain.matchings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingsRepository extends JpaRepository<Matchings, Long> {
    Iterable<Matchings> findAllByAuthorid(Long authorid);
}
