package az.company.springcache.service;

import az.company.springcache.domain.Student;
import az.company.springcache.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    /**
     * When a new student is added,
     * we would like to update the list of students in the cache.
     * Therefore, we need another annotation to keep the cache out of date specified:
     *
     * @CacheEvict annotation update cache
     * <p>
     * We must also set 'allEntries' to true because we want it to delete all of its data regardless of conditions.
     **/
    @CacheEvict(value = "allStudents", allEntries = true)
    public void save(Student student) {
        studentRepository.save(student);
    }


    /**
     * when we get student by id, information stored in cache
     **/
    @Cacheable("student")
    public Student getStudentById(String id) {

        return studentRepository.getById(id);
    }

    /**
     * also here information stored in cache
     **/
    @Cacheable("allStudents")
    public Map<String, Student> getAll() {
        return studentRepository.getAll();
    }
}
