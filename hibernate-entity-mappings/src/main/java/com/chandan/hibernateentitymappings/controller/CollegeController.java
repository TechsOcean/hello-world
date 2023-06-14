package com.chandan.hibernateentitymappings.controller;

import com.chandan.hibernateentitymappings.mysql.College;
import com.chandan.hibernateentitymappings.postgresRepository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeRepository collegeRepository;

    @PostMapping
    public ResponseEntity<College> postCollege(@RequestBody College college) {
        return new ResponseEntity<>(collegeRepository.save(college), HttpStatus.OK);
    }
}
