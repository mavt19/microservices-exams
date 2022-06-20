package com.bns.microservices.exams.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bns.microservices.exams.models.entity.Course;
import com.bns.microservices.exams.models.entity.Exam;
import com.bns.microservices.exams.models.entity.Question;
import com.bns.microservices.exams.services.ExamService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ExamController {

	private final ExamService examService;

	@GetMapping
	public ResponseEntity<?> getAllExams() {

		return ResponseEntity.ok().body(examService.findAll());
	}

	@GetMapping("/page")
	public ResponseEntity<?> getAllExams(Pageable pageable) {

		return ResponseEntity.ok().body(examService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getExamById(@PathVariable("id") Long id) {
		Optional<Exam> examOpt = examService.finById(id);
		if (examOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(examOpt.get());
	}

	@PostMapping
	public ResponseEntity<?> createExam(@Valid @RequestBody Exam exam, BindingResult result) {
		if(result.hasErrors()) {
			return validate(result);
		}
		 List<Question> questions = new ArrayList<>();
		 Exam e = exam;
		exam.getQuestions().forEach(x-> {
			x.setExam(exam);
			questions.add(x);
		});
		e.setQuestions(questions);
		Exam examDb = examService.save(e);
		return ResponseEntity.status(HttpStatus.CREATED).body(examDb);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateExam(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			return validate(result);
		}
		Optional<Exam> examOpt = examService.finById(id);
		if (examOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		exam.setId(id);
//		List<Question> deletedQuestions = new ArrayList<>();
//		
//		examOpt.get().getQuestions().parallelStream()
//		.filter(x-> exam.getQuestions()!=null && !exam.getQuestions().contains(x))
//		.forEach(deletedQuestions::add);
		
//		 List<Question> questions = new ArrayList<>();
//		 Exam e = exam;
////		 examOpt.get().getQuestions().forEach(x-> {
////			x.setExam(examOpt.get());
////			questions.add(x);
////		});
//			examOpt.get().getQuestions().stream()
//			.filter(x-> !exam.getQuestions().contains(x))
//			.forEach(x-> {
//				x.setExam(examOpt.get());
//				questions.add(x);
//			});
//			questions.forEach(x->{
//				System.out.println(x.getId() + x.getText());
//			});
//		e.setQuestions(questions);
		 List<Question> questions = new ArrayList<>();
		 Exam e = exam;
		exam.getQuestions().forEach(x-> {
			x.setExam(exam);
			questions.add(x);
		});
		e.setQuestions(questions);
		questions.forEach(x->{
			System.out.println(" --- "+x.getId() + x.getText());
		});
		Exam examDb = examService.save(exam);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(examDb);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExam(@PathVariable("id") Long id) {
		Optional<Exam> examOpt = examService.finById(id);
		if (examOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		examService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/get-exams-by-name/{term}")
	public ResponseEntity<?> getExamsByName(@PathVariable("term") String term) {
		
		List<Exam> exams = (List<Exam>) examService.findExamByName(term);
		if (exams == null || exams.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(exams);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourses() {

		return ResponseEntity.ok().body(examService.findAllCourses());
	}
	
	@GetMapping("/courses/page")
	public ResponseEntity<?> getAllCourses(Pageable pageable) {

		return ResponseEntity.ok().body(examService.findAllCourses(pageable));
	}
	
	@PostMapping("/courses")
	public ResponseEntity<?> createCourses(@RequestBody List<Course> courses) {

		Iterable<Course>  coursesDb = examService.saveCourses(courses);
		return ResponseEntity.status(HttpStatus.CREATED).body(coursesDb);
	}
	
	@PostMapping("/courses/{id}")
	public ResponseEntity<?> updateCourses(@RequestBody List<Course> courses, @PathVariable("id") Long id) {
		Optional<Course> courseOpt = examService.finCourseById(id);
		if(courseOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
//		Course courseFather = courseOpt.get();
//		Course father = new Course();
//		father.setId(id);
//		
//		courseFather.setFather(father);
		courses.forEach(x->{
			x.setFather(courseOpt.get());
		});
		System.out.println(courses);
		Iterable<Course>  coursesDb = examService.saveCourses(courses);
		return ResponseEntity.status(HttpStatus.CREATED).body(coursesDb);
	}
	
	@GetMapping("/answered-by-questions")
	public ResponseEntity<?> answeredByQuestionsIds(@RequestParam List<Long> questionsIds){
		List<Long> examsIds = (List<Long>) examService.findExamenIdsWithAnswersByQuestionIds(questionsIds);
		return ResponseEntity.ok().body(examsIds );
	}
	private static ResponseEntity<?> validate(BindingResult result){
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(x->{
			errors.put(x.getField(), " the field : "+ x.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
}
