package com.namgoo.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	// 질문 목록 조희(페이징)
	public Page<Question> findAll(Pageable pageable);
	
	// 질문 단일 조회
	public Optional<Question> findById(Integer id);
	
}
