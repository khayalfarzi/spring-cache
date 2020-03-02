package az.company.springcache.controller;

import az.company.springcache.domain.Student;
import az.company.springcache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/save")
    public void save(@RequestBody Student student) {
        studentService.save(student);
    }

    @GetMapping("/all")
    public Map<String, Student> findAll() throws InterruptedException {
        return studentService.getAll();
    }

    @GetMapping("/get/{id}")
    public Student findStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void update(Student student) {
        studentService.update(student);
    }
}
