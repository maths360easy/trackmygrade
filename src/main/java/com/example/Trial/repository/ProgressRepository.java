package com.example.Trial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Progress;
import com.example.Trial.model.Student;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Progress findByStudent(Student student);
      @Transactional
    void deleteByStudentStudentId(Long studentId);

    

}
