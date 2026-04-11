package com.example.Trial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Trial.model.Attendance;
import com.example.Trial.model.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudent(Student student);

@Query("SELECT a.attendancePercent FROM Attendance a WHERE a.student.studentId = :id ORDER BY a.amonth")
List<Integer> findAttendanceByStudent(@Param("id") Long id);
List<Attendance> findByStudentStudentId(Long studentId);


}




