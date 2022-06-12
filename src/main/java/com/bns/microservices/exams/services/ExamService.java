package com.bns.microservices.exams.services;

import java.util.List;
import java.util.Optional;

import com.bns.microservices.exams.models.entity.Course;
import com.bns.microservices.exams.models.entity.Exam;

public interface ExamService {

	public Iterable<Exam> findAll();
	
	public Optional<Exam> finById(Long id);
	
	public Exam save(Exam exam);
	
	public void deleteById(Long id);
	
	public List<Exam> findExamByName(String term);
	
	public List<Course> findAllCourses();

	public List<Course> saveCourses(List<Course> courses);
	
	public Optional<Course> finCourseById(Long id);

	public Course saveCourse(Course courseFather);
}
