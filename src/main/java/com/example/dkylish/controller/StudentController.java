package com.example.dkylish.controller;

import java.util.List;

import com.example.dkylish.entity.Student;
import com.example.dkylish.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping("/students")
    public void addStudent(@RequestParam("name") String name, @RequestParam("faculty") String faculty,
                              @RequestParam("age") int age) {
        studentService.addStudent(name, faculty, age);
    }

    @DeleteMapping("/students")
    public void deleteStudent(@RequestParam("name") String name) {
        studentService.deleteStudent(name);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestParam("id") Long id, @RequestParam("faculty") String faculty) {
       return studentService.updateStudent(id, faculty);
    }

}
