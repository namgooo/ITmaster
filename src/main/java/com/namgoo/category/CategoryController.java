package com.namgoo.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 목록
	@GetMapping("/category-list")
	public String findCategoryList(Model model) {
		List<Category> categoryList = this.categoryService.findCategoryList();
		model.addAttribute("categoryList", categoryList);
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
