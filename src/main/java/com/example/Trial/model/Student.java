package com.example.Trial.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "student")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    private String name;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Course course;

    @Column(name = "fees_paid")
    private Integer feesPaid;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Attendance> attendance;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Marks> marks;


    public Student() {
    this.course = new Course();   
          }  

    // GETTERS + SETTERS

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(Integer feesPaid) {
        this.feesPaid = feesPaid;
    }
}
