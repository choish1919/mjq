package com.choish.mjq.domain.notices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticesRepository extends JpaRepository<Notices, Long> {
    Iterable<Notices> findAllByOrderByIdDesc();
    Page<Notices> findAllByOrderByIdDesc(Pageable pageable);
}
