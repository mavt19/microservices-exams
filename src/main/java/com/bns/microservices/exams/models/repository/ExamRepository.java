package com.bns.microservices.exams.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bns.microservices.exams.models.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{

	@Query(nativeQuery = true,value =  "select * from exams where name like %?1%")
	public List<Exam> findExamByName(String term);
}
