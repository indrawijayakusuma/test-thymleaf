package com.example.testthymeleaf.service;

import com.example.testthymeleaf.entity.Student;
import com.example.testthymeleaf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll(){
        List<Student> all = studentRepository.findAll();
        return all;
    }

}
