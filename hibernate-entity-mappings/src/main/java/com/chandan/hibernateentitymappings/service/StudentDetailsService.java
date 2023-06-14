package com.chandan.hibernateentitymappings.service;

import com.chandan.hibernateentitymappings.entities.StudentDetails;
import com.chandan.hibernateentitymappings.repository.StudentDetailRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class StudentDetailsService {

    private StudentDetailRepository studentDetailRepository;

    public StudentDetailsService(StudentDetailRepository studentDetailRepository) {
        System.out.println(" ========  StudentDetailsService ------  " +
                "Constructor ========= ");
        this.studentDetailRepository = studentDetailRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(" ========  StudentDetailsService ------  " +
                "@EventListener.ContextRefreshedEventinitializatonMethod ========= ");
    }

    public ResponseEntity<StudentDetails> saveStudent(@Valid StudentDetails studentDetails) {
        return new ResponseEntity<>(studentDetailRepository.save(studentDetails), HttpStatus.CREATED);
    }
}
