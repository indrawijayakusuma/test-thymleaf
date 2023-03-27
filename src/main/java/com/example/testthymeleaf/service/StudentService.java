package com.example.testthymeleaf.service;

import com.example.testthymeleaf.entity.Student;
import com.example.testthymeleaf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Student> paginate(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return studentRepository.findAll(pageable);
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
