package az.company.springcache.service;

import az.company.springcache.domain.Student;
import az.company.springcache.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
     * While @CacheEvict reduces the overhead of looking up entries in a large cache by removing stale and unused entries,
     * ideally, you want to avoid evicting too much data out of the cache.
     * Instead, you'd want to selectively and intelligently update the entries whenever they're altered.
     *
     * With the @CachePut annotation, you can update the content of the cache without interfering the method execution.
     * That is, the method would always be executed and the result cached.
     *
     * reference: https://www.baeldung.com/spring-cache-tutorial
    * */
    @CachePut(value = "allStudents")
    public void update(Student student) {
        studentRepository.update(student);
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
    public Map<String, Student> getAll() throws InterruptedException {

        Thread.sleep(5000);

        return studentRepository.getAll();
    }
}
