package com.bns.microservices.exams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bns.microservices.exams.models.entity.Course;
import com.bns.microservices.exams.models.entity.Exam;

public interface ExamService {

	public Iterable<Exam> findAll();
	
	public Page<Exam> findAll(Pageable pageable);
	
	public Optional<Exam> finById(Long id);
	
	public Exam save(Exam exam);
	
	public void deleteById(Long id);
	
	public Iterable<Exam> findExamByName(String term);
	
	public Iterable<Course> findAllCourses();

	public Page<Course> findAllCourses(Pageable pageable);
	
	public Iterable<Course> saveCourses(List<Course> courses);
	
	public Optional<Course> finCourseById(Long id);

	public Course saveCourse(Course courseFather);
	
	public Iterable<Long> findExamenIdsWithAnswersByQuestionIds(Iterable<Long> questionsIds); 
}
