package com.example.Trial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Trial.model.Progress;
import com.example.Trial.model.Student;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Progress findByStudent(Student student);
}
