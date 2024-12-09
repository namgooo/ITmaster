package com.namgoo.question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
public class QuestionController {

	// 2024-12-09 퇴근 전 커밋
	
	@Autowired
	private QuestionService questionService;
	
	// 질문 목록 조회(페이징)
	@GetMapping("/question-list")
	public String findQuestionPagingList(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Question> questionList = this.questionService.findQuestionPagingList(pageable);
 		model.addAttribute("questionList", questionList);
 		
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", questionList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", questionList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", questionList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", questionList.getTotalPages()); // 총 페이지 수
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", questionList.getTotalPages()); // 마지막 페이지
		
		return "question/question_list";
	}
	
	// 질문 등록
	@PostMapping("/question-create")
	public String createQuestion(QuestionDTO dto) {
		this.questionService.createQuestion(dto);
		return "redirect:/question/question-list";
	}
	
	// 질문 상세
	@GetMapping("/question-detail/{id}")
	public String detailQuestion(@PathVariable("id") Integer id, Model model) {
		Question question = this.questionService.detailQuestion(id);
		model.addAttribute("question", question);
		return "question/question_detail";
	}
	

}
