package com.namgoo.category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.namgoo.department.Department;
import com.namgoo.employee.Employee;
import com.namgoo.maker.Maker;
import com.namgoo.product.Product;
import com.namgoo.product_info.ProductInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// 카테고리 검색
	private Specification<Category> search(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Category> c, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate filterPredicate = cb.or(
						cb.like(c.get("category"), "%" + keyword + "%")	
				);
				query.distinct(true);
				query.orderBy(cb.desc(c.get("createDate")));
				return filterPredicate;
			}
		};
	}
	
	// 카테고리 검색 목록(페이징)
	public Page<Category> findCategoryPagingList(String keyword, Pageable pageable) {
		Specification<Category> specification = search(keyword);
		Page<Category> categoryList = this.categoryRepository.findAll(specification, pageable);
		return categoryList;
	}
	
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
