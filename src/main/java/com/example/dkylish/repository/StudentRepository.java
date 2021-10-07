package com.example.dkylish.repository;

import java.util.Optional;

import com.example.dkylish.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteByName(String name);

    Optional<Student> findByNameAndFacultyAndAge(String name, String faculty, int id);
}
