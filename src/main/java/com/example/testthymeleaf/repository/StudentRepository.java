package com.example.testthymeleaf.repository;

import com.example.testthymeleaf.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
