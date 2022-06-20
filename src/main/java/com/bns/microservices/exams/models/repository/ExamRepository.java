package com.bns.microservices.exams.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bns.microservices.exams.models.entity.Exam;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long>{

	@Query(nativeQuery = true,value =  "select * from exams where name like %?1%")
	public List<Exam> findExamByName(String term);
	@Query("select e.id from Question q join q.exam e where q.id in ?1 group by e.id")
	public Iterable<Long> findExamenIdsWithAnswersByQuestionIds(Iterable<Long> questionsIds); 
}
