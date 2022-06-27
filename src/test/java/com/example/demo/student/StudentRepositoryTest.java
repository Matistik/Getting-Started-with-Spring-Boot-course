package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static java.util.Calendar.APRIL;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {

        Student student =  Student.builder().name("MIso").email("miso@miso.miso").dob(LocalDate.of(2000, APRIL, 2)).build();
        entityManager.persist(student);
    }

    @Test
    public void whenFindById_thenReturnStudent(){
        Student student = studentRepository.findById(1L).get();
        assertEquals(student.getName(), "MIso");
    }

}