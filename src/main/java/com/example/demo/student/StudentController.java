package com.example.demo.student;

import com.example.demo.error.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

   @Autowired //DI znamena ze viac controllerov v systeme dokaze pouzivat jednu servicu
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return  studentService.getStudents();

    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") Long studentId) throws StudentNotFoundException {
        return  studentService.getStudentById(studentId);

    }

    @GetMapping("/name/{name}")
    public Student getStudentByName(@PathVariable("name") String studentName) throws StudentNotFoundException {
        return  studentService.getStudentByName(studentName);

    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);

    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              @PathVariable Long studentId){
       studentService.updateStudent(name,email, studentId);
    }



}
