package com.example.Trial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Trial.model.Student;
import com.example.Trial.repository.CourseRepository;
import com.example.Trial.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentRegistrationController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentRegistrationController(StudentRepository studentRepo,
                                         CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseRepo.findAll());
        return "student-register";
    }

    @PostMapping("/save-student")
    public String saveStudent(Student student) {

        Long cid = student.getCourse().getId();   
        student.setCourse(courseRepo.findById(cid).orElse(null));

        studentRepo.save(student);
        return "redirect:/admin/dashboard";
    }
}
