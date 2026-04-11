package com.example.Trial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Trial.model.Marks;
import com.example.Trial.model.Student;


   public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findByStudent(Student student);
   List<Marks> findByStudentStudentId(Long studentId);




    @Query("SELECT m.testMonth FROM Marks m WHERE m.student.studentId = :id ORDER BY m.testMonth")
    List<String> findMonthsByStudent(@Param("id") Long id);

    @Query("SELECT m.marks FROM Marks m WHERE m.student.studentId = :id ORDER BY m.testMonth")
    List<Integer> findMarksByStudent(@Param("id") Long id);
}
