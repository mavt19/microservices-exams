package com.bns.microservices.exams.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bns.microservices.exams.models.entity.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long>{

}
