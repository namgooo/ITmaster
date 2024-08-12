package com.namgoo.category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// 카테고리 목록
	public List<Category> findCategoryList () {
		List<Category> categoryList = this.categoryRepository.findAll();
		return categoryList;
	}
	
	// 카테고리 등록
	public void createCategory (CategoryDTO dto) {
		Category category = new Category();
		category.setCategory(dto.getCategory());
		category.setCreateDate(LocalDateTime.now());
		this.categoryRepository.save(category);
	}
	
	// 카테고리 삭제
	public void deleteCategory (Integer id) {
		this.categoryRepository.deleteById(id);
	}
	
	// 카테고리 상세
	public Optional<Category> getCategoryDetail (Integer id) {
		Optional<Category> categoryDetail = this.categoryRepository.findById(id);
		return categoryDetail;
	}
	
	// 카테고리 수정
	public void updateCategory (CategoryDTO dto) {
		Category category = this.categoryRepository.findById(dto.getId()).orElse(null);
		category.setCategory(dto.getCategory());
		category.setCreateDate(LocalDateTime.now());
		this.categoryRepository.save(category);
	}

}
