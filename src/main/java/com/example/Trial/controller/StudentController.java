package com.example.Trial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Trial.model.Progress;
import com.example.Trial.model.Student;
import com.example.Trial.repository.AttendanceRepository;
import com.example.Trial.repository.MarksRepository;
import com.example.Trial.repository.ProgressRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private MarksRepository marksRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private ProgressRepository progressRepo;


@GetMapping("/dashboard")
public String dashboard(HttpSession session, Model model) {

    Student student = (Student) session.getAttribute("loggedStudent");

    if (student == null) {
        return "redirect:/student/login";
    }

    model.addAttribute("student", student);

    // -------------------------
    // 1. Handle Progress 
    // -------------------------
    Progress progress = progressRepo.findByStudent(student);

    int progressValue = 0; // default
    if (progress != null) {
        progressValue = progress.getProgress();
    }

    model.addAttribute("progress", progressValue);

    // -------------------------
    // 2. Handle Marks 
    // -------------------------
    List<String> monthsList = marksRepo.findMonthsByStudent(student.getStudentId());
    List<Integer> marksList = marksRepo.findMarksByStudent(student.getStudentId());

    if (monthsList == null || monthsList.isEmpty()) {
        monthsList = List.of();
    }
    if (marksList == null || marksList.isEmpty()) {
        marksList = List.of();
    }

    model.addAttribute("monthsList", monthsList);
    model.addAttribute("marksList", marksList);

    // -------------------------
    // 3. Handle Attendance 
    // -------------------------
    List<Integer> attendanceList = attendanceRepo.findAttendanceByStudent(student.getStudentId());

    if (attendanceList == null || attendanceList.isEmpty()) {
        attendanceList = List.of();
    }

    model.addAttribute("attendanceList", attendanceList);

    return "student-dashboard";
}

}
