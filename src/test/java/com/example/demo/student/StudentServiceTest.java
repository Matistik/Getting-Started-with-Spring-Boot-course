package com.example.demo.student;

import com.example.demo.error.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Calendar.APRIL;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {

        Student student =  Student.builder().name("MIso").email("miso@miso.miso").dob(LocalDate.of(2000, APRIL, 2)).build();
        Mockito.when(studentRepository.findStudentByName("MIso")).thenReturn(Optional.ofNullable(student));


    }

    @Test
    @DisplayName("Get data based on valid student name")
    public void whenValidStudentName_ShouldPass() throws StudentNotFoundException {
        String studentName = "MIso";
        Student foundStudent = studentService.getStudentByName(studentName);

        assertEquals(studentName, foundStudent.getName());
        assertEquals("miso@miso.miso", foundStudent.getEmail());



    }

    @Test
    public void whenValidStudentName_ShouldFail() throws StudentNotFoundException {
        String studentName = "nejakemeno";
        Student foundStudent = studentService.getStudentByName(studentName);





    }

}