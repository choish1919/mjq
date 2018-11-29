package com.choish.mjq.domain.notices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticesRepository extends JpaRepository<Notices, Long> {
    List<Notices> findAllByOrderByIdDesc();
    Page<Notices> findAllByOrderByIdDesc(Pageable pageable);
}
