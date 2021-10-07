package com.example.dkylish.service;

import java.util.List;

import com.example.dkylish.entity.Student;
import com.example.dkylish.exception.StudentException;
import com.example.dkylish.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public void addStudent(String name, String faculty, int age) {
        var student = new Student().setName(name).setFaculty(faculty).setAge(age);
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(String name) {
        studentRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, String faculty) {
        var student = studentRepository.findById(id).orElseThrow(() -> new StudentException("id", id.toString(),
                "Student is not found", StudentException.Status.NOT_FOUND));
        studentRepository.save(student);
        return student;
    }

}
