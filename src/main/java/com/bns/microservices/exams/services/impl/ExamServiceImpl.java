package com.bns.microservices.exams.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bns.microservices.exams.models.entity.Course;
import com.bns.microservices.exams.models.entity.Exam;
import com.bns.microservices.exams.models.repository.CourseRepository;
import com.bns.microservices.exams.models.repository.ExamRepository;
import com.bns.microservices.exams.services.ExamService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService{

	private final ExamRepository examRepository;
	
	private final CourseRepository courseRepository;
	
	@Override
	public Iterable<Exam> findAll() {
		// TODO Auto-generated method stub
		return examRepository.findAll();
	}

	@Override
	public Optional<Exam> finById(Long id) {
		// TODO Auto-generated method stub
		return examRepository.findById(id);
	}

	@Override
	public Exam save(Exam exam) {
		// TODO Auto-generated method stub
		return examRepository.save(exam);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		examRepository.deleteById(id);
	}

	@Override
	public List<Exam> findExamByName(String term) {
		// TODO Auto-generated method stub
		return examRepository.findExamByName(term);
	}

	@Override
	public Iterable<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public Iterable<Course> saveCourses(List<Course> courses) {
		// TODO Auto-generated method stub
		return courseRepository.saveAll(courses);
	}

	@Override
	public Optional<Course> finCourseById(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.findById(id);
	}

	@Override
	public Course saveCourse(Course courseFather) {
		// TODO Auto-generated method stub
		return courseRepository.save(courseFather);
	}

	@Override
	public Page<Exam> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return examRepository.findAll(pageable);
	}

	@Override
	public Page<Course> findAllCourses(Pageable pageable) {
		// TODO Auto-generated method stub
		return courseRepository.findAll(pageable);
	}

	@Override
	public Iterable<Long> findExamenIdsWithAnswersByQuestionIds(Iterable<Long> questionsIds) {
		// TODO Auto-generated method stub
		return examRepository.findExamenIdsWithAnswersByQuestionIds(questionsIds);
	}

}
