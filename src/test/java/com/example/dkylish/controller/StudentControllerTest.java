package com.example.dkylish.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;

import com.example.dkylish.entity.Student;
import com.example.dkylish.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new JsonMapper()
            .registerModule(new JavaTimeModule());

    MockMvc mockMvc;

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    @SneakyThrows
    @Disabled
    void shouldGetStudents() {
        var student = createStudent();
        given(studentService.findAllStudents()).willReturn(List.of(student));

        var actualResponse = mockMvc.perform(
                MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        var actualStudents = OBJECT_MAPPER.readValue(actualResponse.getContentAsString(),
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class));

        assertThat(actualStudents).isEqualTo(List.of(student));
    }

    @Test
    @SneakyThrows
    void shouldAddStudent() {
        var student = createStudent();
        var actualResponse = mockMvc.perform(MockMvcRequestBuilders.post("/students?name=Petro&faculty=math&age=1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();

        assertThat(actualResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(studentService).addStudent(student.getName(), student.getFaculty(), 1);
    }

    @Test
    @SneakyThrows
    void shouldDeleteStudent() {
        var student = createStudent();
        var actualResponse = mockMvc.perform(MockMvcRequestBuilders.delete("/students?name=Petro")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();

        assertThat(actualResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(studentService).deleteStudent(student.getName());
    }

    @Test
    @SneakyThrows
    void shouldUpdateStudent() {
        var student = createStudent();
        given(studentService.updateStudent(any(), any())).willReturn(student);

        var actualResponse = mockMvc.perform(MockMvcRequestBuilders.put("/students?id=1&faculty=math")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();

        var actualStudent = OBJECT_MAPPER.readValue(actualResponse.getContentAsString(), Student.class);
        assertThat(actualResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualStudent).hasFieldOrPropertyWithValue("faculty", "math");
    }


    private Student createStudent() {
        return new Student().setAge(1).setName("Petro").setFaculty("math");
    }

}
