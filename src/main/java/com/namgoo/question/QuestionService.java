package com.namgoo.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	// 질문 목록 조회(페이징)
	public Page<Question> findQuestionPagingList(Pageable pageable) {
		Page<Question> questionList = this.questionRepository.findAll(pageable);
		return questionList;
	}
	
	// 질문 등록
	public void createQuestion(QuestionDTO dto) {
				
		Question question = new Question();
		
		question.setSubject(dto.getSubject());
		question.setContent(dto.getContent());
		question.setCreateDate(LocalDateTime.now());
		
		this.questionRepository.save(question);
	}
	
	// 질문 단일 조회
	public Question detailQuestion(Integer id) {
		Question question = this.questionRepository.findById(id).orElse(null);
		return question;
	}

}
