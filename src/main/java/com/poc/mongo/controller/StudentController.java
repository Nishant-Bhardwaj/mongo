package com.poc.mongo.controller;

import com.poc.mongo.model.Student;
import com.poc.mongo.repository.StudentRepository;
import com.poc.mongo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProducerService producerService;

    @PostMapping("/add/{school}")
    public ResponseEntity<Object> addStudent(@RequestBody Student student, @PathVariable String school){
        student.setSchool(school);
        student = studentRepository.save(student);

        // Sending to Admin Rabbit Queue:
        producerService.produceMessageToAdminQueue(student);

        // Sending to Admin Rabbit Queue:
        producerService.produceMessageToStudentQueue(student);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<Object> getAllStudents(){
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable String id){
        return new ResponseEntity<>(studentRepository.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable String id){
        studentRepository.deleteById(id);
        return new ResponseEntity<>("Delete student with roll: "+ id, HttpStatus.OK);
    }
}
