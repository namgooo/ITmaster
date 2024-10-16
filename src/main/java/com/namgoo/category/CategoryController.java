package com.namgoo.category;

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

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 검색 목록(페이징)
	@GetMapping("/category-list")
	public String findCategoryPagingList(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		Page<Category> categoryList = this.categoryService.findCategoryPagingList(keyword, pageable);
		model.addAttribute("categoryList", categoryList);
		
		// 페이징
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); // 이전 페이지 번호
		model.addAttribute("next", pageable.next().getPageNumber()); // 다음 페이지 번호
		model.addAttribute("hasPrevious", categoryList.hasPrevious()); // 이전 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("hasNext", categoryList.hasNext()); // 다음 페이지가 있는지 여부 확인 (boolean)
		model.addAttribute("currentPage", categoryList.getNumber()); // 현재 페이지 번호 (0부터 시작)
		model.addAttribute("totalPages", categoryList.getTotalPages()); // 총 페이지 수
		model.addAttribute("keyword", keyword); // 검색 시 키워드
		model.addAttribute("first", pageable.first().getPageNumber()); // 첫 페이지
		model.addAttribute("totalPages", categoryList.getTotalPages()); // 마지막 페이지
		
		return "category/category_list";
	}
		
	// 카테고리 등록
	@PostMapping("/category-create")
	public String createCategory(CategoryDTO dto) {
		this.categoryService.createCategory(dto);
		return "redirect:/category/category-list";
	}
	
	// 카테고리 삭제
	@GetMapping("/category-delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {
		this.categoryService.deleteCategory(id);
		return "redirect:/category/category-list";
	}
	
	// 카테고리 상세
	@GetMapping("/category-detail/{id}")
	public String getCategoryDetail(@PathVariable("id") Integer id, Model model) {
		Category category = this.categoryService.getCategoryDetail(id).orElse(null);
		model.addAttribute("category", category);
		return "category/category_detail";
	}
	
	// 카테고리 수정
	@PostMapping("/category-update")
	public String updateCategory(CategoryDTO dto) {
		this.categoryService.updateCategory(dto);
		return "redirect:/category/category-list";
	}
	
}
