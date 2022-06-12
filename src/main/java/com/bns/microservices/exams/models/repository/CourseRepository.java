package com.bns.microservices.exams.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bns.microservices.exams.models.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
