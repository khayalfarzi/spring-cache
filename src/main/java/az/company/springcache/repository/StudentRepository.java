package az.company.springcache.repository;

import az.company.springcache.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    private static final Map<String, Student> STUDENT_MAP = new HashMap<>();

    public void save(Student student) {
        STUDENT_MAP.put(student.getId(), student);
    }

    public Map<String, Student> getAll() {
        return STUDENT_MAP;
    }

    public Student getById(String id) {
        return STUDENT_MAP.get(id);
    }

}
