package com.example.testthymeleaf.service;

import com.example.testthymeleaf.entity.Student;
import com.example.testthymeleaf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll(){
        List<Student> all = studentRepository.findAll();
        return all;
    }
    public Student findByid(Integer id){
        Student byId = studentRepository.findById(id).get();
        return byId;
    }
    public Student addStudent(){
        return new Student();
    }
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    public void deleteById(Integer id){
         studentRepository.deleteById(id);
    }
    public Optional<Student> editById(Integer id){
        return studentRepository.findById(id);
    }
}
