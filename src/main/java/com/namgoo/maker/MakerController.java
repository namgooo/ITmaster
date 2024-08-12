package com.namgoo.maker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.namgoo.category.CategoryService;

@Controller
@RequestMapping("/maker")
public class MakerController {
	
	@Autowired
	private MakerService makerService;
	@Autowired
	private CategoryService categoryService;
	
	// 제조사 목록
	@GetMapping("/maker-list")
	public String findMakerList(Model model) {
		List<Maker> makerList = this.makerService.findMakerList();
		model.addAttribute("makerList", makerList);
		return "maker/maker";
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
