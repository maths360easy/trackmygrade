package com.example.Trial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Trial.model.Student;
import com.example.Trial.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentLoginController {

    @Autowired
    private StudentRepository studentRepo;

    // ✅ Open student login page
    @GetMapping("/login")
    public String loginPage() {
        return "student-login";
    }

    // ✅ Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Optional<Student> studentOpt = studentRepo.findByEmail(email);

        if (studentOpt.isPresent() &&
            studentOpt.get().getPassword().equals(password)) {

            session.setAttribute("loggedStudent", studentOpt.get());

            return "redirect:/student/dashboard";

        } else {
            model.addAttribute("error", "Invalid student login");
            return "student-login";
        }
    }

    // ✅ Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
}

