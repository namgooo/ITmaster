package com.namgoo.maker;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.namgoo.category.CategoryService;

@Controller
@RequestMapping("/maker")
public class MakerController {
	
	@Autowired
	private MakerService makerService;
	@Autowired
	private CategoryService categoryService;
	
	// 제조사 검색 목록(페이징)
	@GetMapping("/maker-list")
	public String findMakerPagingList(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		Page<Maker> makerList = this.makerService.findMakerPagingList(keyword, pageable);
		model.addAttribute("makerList", makerList);
		
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", makerList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", makerList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", makerList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", makerList.getTotalPages()); // 총 페이지 수
		model.addAttribute("keyword", keyword); // 검색 시 키워드
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", makerList.getTotalPages()); // 마지막 페이지

		return "maker/maker_list";
	}
	
	
	// 제조사 등록
	@PostMapping("/maker-create")
	public String createMaker(MakerDTO dto) {
		this.makerService.createMaker(dto);
		return "redirect:/maker/maker-list";
	}
	
	// 제조사 삭제
	@GetMapping("/maker-delete/{id}")
	public String deleteMaker(@PathVariable("id") Integer id) {
		this.makerService.deleteMaker(id);
		return "redirect:/maker/maker-list";
	}
	
	// 제조사 상세
	@GetMapping("/maker-detail/{id}")
	public String getMakerDetail(@PathVariable("id") Integer id, Model model) {
		Maker maker = this.makerService.getMakerDetail(id);
		model.addAttribute("maker", maker);
		return "maker/maker_detail";
	}
	
	// 제조사 수정
	@PostMapping("/maker-update")
	public String updateMaker(MakerDTO dto) {
		this.makerService.updateMaker(dto);
		return "redirect:/maker/maker-list";
	}
	
}
