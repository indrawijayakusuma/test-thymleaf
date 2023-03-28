package com.example.testthymeleaf.controller;

import com.example.testthymeleaf.entity.Student;
import com.example.testthymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String listStudents(Model model){
//        try {
//            List<Student> students = new ArrayList<>();
//            studentService.getAll().forEach(students::add);
//            model.addAttribute("students", students);
//        } catch (Exception e) {
//            model.addAttribute("message", e.getMessage());
//        }

//        List<Student> all = studentService.getAll();
//        model.addAttribute("students", all);
//        return "index";
        return paginate(null,1,"name", "asc",model);

    }
    @GetMapping("/students/new")
    public String addStudentForm(Model model){
        Student student = studentService.addStudent();
        model.addAttribute("students", student);
        return "studentform";
    }
    @PostMapping("/students")
    public String saveStudent(Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/students/delete/{id}")
    public String delete(@PathVariable Integer id){
        studentService.deleteById(id);
        return "redirect:/students";
    }
    @GetMapping("/students/update/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model){
        Student byid = studentService.findByid(id);
        model.addAttribute("students", byid);
        return "updateForm";
    }

    @GetMapping("/students/page/{no}")
    public String paginate(
        @RequestParam(required = false) String keyword,
        @PathVariable(value = "no") int pageNo,
        @RequestParam(defaultValue = "name") String sortField,
        @RequestParam(defaultValue = "asc") String sortDir, Model model
        ){
        int pageSize = 5;

        Page<Student> page = null;
        if (keyword == null){
            page = studentService.paginate(pageNo, pageSize, sortField, sortDir);
        }else {
             page = studentService.searching(pageNo, pageSize, sortField, sortDir, keyword);
             model.addAttribute("keyword", keyword);
        }

        List<Student> studentList = new ArrayList<Student>();
        studentList = page.getContent();

        model.addAttribute("currentPage" , pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("students", studentList);

        model.addAttribute("sortField" , sortField);
        model.addAttribute("sortDir" , sortDir);
        model.addAttribute("reverseSortDir" , sortDir.equals("asc")?"desc":"asc");
        return "index";
    }

}
