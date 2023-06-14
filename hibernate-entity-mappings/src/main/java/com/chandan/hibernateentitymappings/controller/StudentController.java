package com.chandan.hibernateentitymappings.controller;

import com.chandan.hibernateentitymappings.entities.Student;
import com.chandan.hibernateentitymappings.exception.StudentNotFoundException;
import com.chandan.hibernateentitymappings.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentsById(@PathVariable(name = "id") int id) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(studentService.registerStudent(student), HttpStatus.CREATED);
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<Student> updateStudentFields(@PathVariable(name = "studentId") Integer studentId, @RequestBody Map<String, Object> fields) throws StudentNotFoundException {

        Optional<Student> student = studentService.getStudent(studentId);

        student.ifPresent(student1 -> fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Student.class, key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, student1, value);
        }));

        return new ResponseEntity<>(studentService.registerStudent(student.get()), HttpStatus.OK);
    }

}
