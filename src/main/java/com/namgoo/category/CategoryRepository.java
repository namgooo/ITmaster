package com.namgoo.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	// 카테고리 검색 목록(페이징)
	public Page<Category> findAll(Specification<Category> specification, Pageable pageable);

	// 카테고리 목록
	public List<Category> findAll();
	
	// 카테고리 단일 조회
	public Optional<Category> findById(Integer id);
	
	// 카테고리명으로 단일 조회
	public Optional<Category> findByCategory(String category);
	
}
