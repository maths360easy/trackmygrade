package com.example.Trial.model;
//import com.example.Trial.model.Course;  

import jakarta.persistence.Column;   // JPA annotations
import jakarta.persistence.Entity;               // Lombok (auto generate getters/setters)
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Course subject;


    @Column(name = "a_month")
     private String amonth;


    @Column(name = "attendance_percent")
    private int attendancePercent;

    public Attendance() {}

    public Attendance(Student student, Course subject, int attendancePercent) {
        this.student = student;
        this.subject = subject;   
        this.attendancePercent = attendancePercent;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAmonth() {
    return amonth;
            }

    public void setAmonth(String amonth) {
    this.amonth = amonth;
            }




    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getSubject() { return subject; }   // ✅ fixed
    public void setSubject(Course subject) { this.subject = subject; } // ✅ fixed

    public int getAttendancePercent() { return attendancePercent; }
    public void setAttendancePercent(int attendancePercent) {
        this.attendancePercent = attendancePercent;
    }
}















