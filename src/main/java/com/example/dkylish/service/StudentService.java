package com.example.dkylish.service;

import java.util.List;

import com.example.dkylish.entity.Student;

public interface StudentService {

    List<Student> findAllStudents();

    void addStudent(String name, String faculty, int age);

    void deleteStudent(String name);

    Student updateStudent(Long id, String faculty);
}
