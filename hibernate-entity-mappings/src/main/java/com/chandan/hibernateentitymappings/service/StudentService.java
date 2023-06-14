package com.chandan.hibernateentitymappings.service;

import com.chandan.hibernateentitymappings.entities.Student;
import com.chandan.hibernateentitymappings.exception.StudentNotFoundException;
import com.chandan.hibernateentitymappings.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements ApplicationListener<ContextRefreshedEvent> {

    private int value = 0;

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(" ========  StudentService ------  " +
                " ApplicationListener<ContextRefreshedEvent> " +
                "@Override.onApplicationEvent =========  value "+ (++value));
    }

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Integer studentId) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new StudentNotFoundException(" Student not found with student id " + studentId);
        }
        return student;
    }

}
