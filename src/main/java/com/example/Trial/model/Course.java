package com.example.Trial.model;

import jakarta.persistence.Column;   // JPA annotations
import jakarta.persistence.Entity;               // Lombok (auto generate getters/setters)
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;
    

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_fee")
    private Integer subjectFee;

    public Course() {}

    public Course(String subjectName, int subjectFee) {
        this.subjectName = subjectName;
        this.subjectFee = subjectFee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public Integer getSubjectFee() { return subjectFee; }
    public void setSubjectFee(Integer subjectFee) { this.subjectFee = subjectFee; }
}