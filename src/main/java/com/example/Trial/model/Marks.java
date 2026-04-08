package com.example.Trial.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marks_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Course subject;

    @Column(name = "test_month")
    private String testMonth;

    @Column(name = "marks")
    private Integer marks;

     public Marks() {
    }
   
    public Marks(Student student, Course subject, String testMonth, Integer marks) {
        this.student = student;
        this.subject = subject;
        this.testMonth = testMonth;
        this.marks = marks;
    }

    public Long getId() { return id; }
    public void setId( Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }



    public Course getSubject() { 
    return subject; 
       } 

    public void setSubject(Course subject) { 
    this.subject = subject;  // ✅ fixed
        }
   

    public String getTestMonth() { return testMonth; }
    public void setTestMonth(String testMonth) { this.testMonth = testMonth; }

    public Integer getMarks() { return marks; }
    public void setMarks(Integer marks) { this.marks = marks; }
}