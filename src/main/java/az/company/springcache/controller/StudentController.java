package az.company.springcache.controller;

import az.company.springcache.domain.Student;
import az.company.springcache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
