package com.namgoo.answer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namgoo.question.Question;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	// 답변 등록
	public void createAnswer(AnswerDTO dto, Question question) {
		
		Answer answer = new Answer();
		
		answer.setReply(dto.getReply());
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		this.answerRepository.save(answer);
		
	}
	
	

}
