package com.example.demo.student;

import com.example.demo.error.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail;
        studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email is taken!");
        }
        studentRepository.save(student);

        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + "not found");
        }
        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(String name, String email, Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail;
            studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            student.setName(email);
        }
    }

    public Student getStudentById(Long id) throws StudentNotFoundException {

        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()){
            throw new StudentNotFoundException("student not available");
        }
        return student.get();




    }

    public Student getStudentByName(String name) throws StudentNotFoundException {

        Optional<Student> student = studentRepository.findStudentByName(name);

        if (!student.isPresent()){
            throw new StudentNotFoundException("student not available");
        }
        return student.get();




    }
}
