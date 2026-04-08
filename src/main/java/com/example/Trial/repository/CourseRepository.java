package com.example.Trial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Trial.model.Course;

//import jakarta.persistence.Id;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Optional: any Course-specific queries, e.g., find by name
}