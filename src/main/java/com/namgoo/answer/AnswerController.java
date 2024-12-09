package com.namgoo.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namgoo.question.Question;
import com.namgoo.question.QuestionService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	
	// 답변 등록
	@PostMapping("/answer-create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, AnswerDTO dto, Model model) {
		Question question = this.questionService.detailQuestion(id);
		this.answerService.createAnswer(dto, question);
		return String.format("redirect:/question/question-detail/%s", id);
	}
	
	
}
