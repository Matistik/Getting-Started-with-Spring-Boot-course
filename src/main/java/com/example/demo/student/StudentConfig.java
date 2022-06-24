package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.APRIL;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student miso = new Student(
                    "MIso",
                    "mito.boss@gmail.com",
                    LocalDate.of(2000, APRIL, 2)

            );
            Student miso2 = new Student(
                    "MIso2",
                    "mito2.boss@gmail.com",
                    LocalDate.of(2000, APRIL, 2)

            );
            repository.saveAll(
                    List.of(miso,miso2)
            );

        };
    }

}
