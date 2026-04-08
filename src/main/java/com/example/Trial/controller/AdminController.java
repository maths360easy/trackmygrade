package com.example.Trial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Trial.model.Admin;
import com.example.Trial.model.Attendance;
import com.example.Trial.model.Course;
import com.example.Trial.model.Marks;
import com.example.Trial.model.Progress;
import com.example.Trial.model.Student;
import com.example.Trial.repository.AdminRepository;
import com.example.Trial.repository.AttendanceRepository;
import com.example.Trial.repository.CourseRepository;
import com.example.Trial.repository.MarksRepository;
import com.example.Trial.repository.ProgressRepository;
import com.example.Trial.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private MarksRepository marksRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private ProgressRepository progressRepo;

    // =============================
    // 1️⃣ LOGIN PAGE
    // =============================
    @GetMapping("/login")
    public String showLogin() {
        return "admin-login";
    }

    // =============================
    // 2️⃣ PROCESS LOGIN
    // =============================
   @PostMapping("/login")
    public String login(@RequestParam String username,
                    @RequestParam String password,
                    HttpSession session,
                    Model model) {

    Optional<Admin> admin = adminRepo.findByUsername(username);

    if (admin.isPresent()) {

        // Compare raw password with hashed password
        boolean passwordMatch = BCrypt.checkpw(password, admin.get().getPassword());

        if (passwordMatch) {
            session.setAttribute("loggedAdmin", admin.get());
            return "redirect:/admin/dashboard";
        }
    }

    model.addAttribute("adminError", "Invalid admin login");
    return "admin-login";
}

    // =============================
    // 3️⃣ DASHBOARD
    // =============================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        if (session.getAttribute("loggedAdmin") == null)
            return "redirect:/admin/login";

        model.addAttribute("student", studentRepo.findAll());
        model.addAttribute("subjects", courseRepo.findAll());
        return "admin-dashboard";
    }

    // =============================
    // 4️⃣ OPEN ADD STUDENT PAGE
    // =============================
    @GetMapping("/add-student")
    public String addStudentPage(HttpSession session, Model model) {

        if (session.getAttribute("loggedAdmin") == null)
            return "redirect:/admin/login";

        //model.addAttribute("studentError", "Invalid student login");
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseRepo.findAll());
        return "add-student";
    }

    // =============================
    // 5️⃣ SAVE STUDENT
    // =============================
 
    @PostMapping("/save-student")
     public String saveStudent(@ModelAttribute("student") Student student,
                          HttpSession session) {

    if (session.getAttribute("loggedAdmin") == null)
        return "redirect:/admin/login";
    
     Course course = courseRepo.findById(student.getCourse().getId()).orElse(null);
        student.setCourse(course);
        student.setFeesPaid(course.getSubjectFee());

     studentRepo.save(student);

    return "redirect:/admin/dashboard";
}
// ---------manage students--------------//

@GetMapping("/managestudent")
public String manageStudent(HttpSession session, Model model) {

    if (session.getAttribute("loggedAdmin") == null)
        return "redirect:/admin/login";

    model.addAttribute("students", studentRepo.findAllByOrderByNameAsc());
    model.addAttribute("attendance", new Attendance());
    model.addAttribute("marks", new Marks());
    model.addAttribute("subjects", courseRepo.findAll());

    // Add total student count
    long totalStudents = studentRepo.count();
    model.addAttribute("totalStudents", totalStudents);

      model.addAttribute("course", new Course());

    return "managestudent";
}


    
    // Add new course
    @PostMapping("/course/add")
    public String addCourse(@ModelAttribute("course") Course course) {
        courseRepo.save(course);
        return "redirect:/admin/managestudent";
    }

    // Edit course
    @GetMapping("/course/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model, HttpSession session) {

        if (session.getAttribute("loggedAdmin") == null)
            return "redirect:/admin/login";

        Course course = courseRepo.findById(id).orElse(null);

        model.addAttribute("course", course);
        model.addAttribute("subjects", courseRepo.findAll());
        model.addAttribute("students", studentRepo.findAllByOrderByNameAsc());

        return "managestudent";
    }

    // Update course
    @PostMapping("/course/update")
    public String updateCourse(@ModelAttribute("course") Course course) {
        courseRepo.save(course);
        return "redirect:/admin/managestudent";
    }

    // Delete course
    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseRepo.deleteById(id);
        return "redirect:/admin/managestudent";
    }


//Progress in Htmlpage


@GetMapping("/progress")
public String progressPage(HttpSession session, Model model) {

    List<Student> students = studentRepo.findAll();
    model.addAttribute("students", students);

    return "progress"; // your HTML file name
}


    // =============================
    // OPEN ATTENDANCE PAGE
    // =============================
    @GetMapping("/attendance")
    public String attendancePage(HttpSession session, Model model) {

    if (session.getAttribute("loggedAdmin") == null)
        return "redirect:/admin/login";

    model.addAttribute("attendance", new Attendance());
    model.addAttribute("students", studentRepo.findAll());
    model.addAttribute("subjects", courseRepo.findAll());

    return "attendance";
    }


    // =============================
    // SAVE ATTENDANCE
    // =============================
    @PostMapping("/save-attendance")
    public String saveAttendance(@ModelAttribute Attendance attendance,
                                 HttpSession session) {

        if (session.getAttribute("loggedAdmin") == null)
            return "redirect:/admin/login";

        attendanceRepo.save(attendance);

        return "redirect:/admin/dashboard";
    }


   
    // =============================
    // OPEN MARKS PAGE
    // =============================
    @GetMapping("/marks")
    public String marksPage(HttpSession session, Model model) {

        //if (session.getAttribute("loggedAdmin") == null)
          //  return "redirect:/admin/login";

        model.addAttribute("marks", new Marks());
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("subjects", courseRepo.findAll());

        return "marks";
    }

    // =============================
    // SAVE MARKS
    // =============================
    @PostMapping("/save-marks")
    public String saveMarks(@ModelAttribute Marks marks,
                            HttpSession session) {

        if (session.getAttribute("loggedAdmin") == null)
            return "redirect:/admin/login";

    System.out.println("Student: " + marks.getStudent());
    System.out.println("Subject: " + marks.getSubject());
    System.out.println("Test Month: " + marks.getTestMonth());
    System.out.println("Marks: " + marks.getMarks());

        marksRepo.save(marks);

        return "redirect:/admin/dashboard";
    }

    // =============================
    // 6️⃣ DELETE STUDENT
    // =============================
 
        @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {

    if (session.getAttribute("loggedAdmin") == null)
        return "redirect:/admin/login";

    studentRepo.deleteById(id);

    return "redirect:/admin/dashboard";
       }


    //edit


    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student s = studentRepo.findById(id).orElse(null);
        if (s == null) return "redirect:/admin/dashboard";

        model.addAttribute("student", s);
        model.addAttribute("subjects", courseRepo.findAll());

        return "edit-student";
    }

        // Progress update
        @PostMapping("/updateProgress")
        public String updateProgress(@RequestParam Long studentId,
                                    @RequestParam int progressValue) {

            Student student = studentRepo.findById(studentId).orElse(null);

            if (student == null) {
                return "redirect:/admin/dashboard";
            }

            Progress progress = progressRepo.findByStudent(student);

            if (progress == null) {
                progress = new Progress();
                progress.setStudent(student);
            }

            progress.setProgress(progressValue);
            progressRepo.save(progress);

            return "redirect:/admin/dashboard";
        }







        // Update -Student


        @PostMapping("/update-student")
        public String updateStudent(@ModelAttribute("student") Student student) {

        Long cid = student.getCourse().getId();
        student.setCourse(courseRepo.findById(cid).orElse(null));

        studentRepo.save(student);

        return "redirect:/admin/managestudent";
    }

    // =============================
    // 7️⃣ LOGOUT
    // =============================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}



