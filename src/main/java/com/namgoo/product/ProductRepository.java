package com.namgoo.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	// 제품 목록
	public List<Product> findAll();
	
	// 제품 단일 조회
	public Optional<Product> findById(Integer id);
	
	// 제품명으로 단일 조회
	public Optional<Product> findByProduct(String product);
	
	// 카테고리 선택 시 해당 제품 조회
	@Query("SELECT p.product FROM Product p JOIN p.category c WHERE c.category = ?1")
	public List<String> findProductsByCategory(String category);
	
	// 제조사 선택 시 해당 제품 조회
	@Query("SELECT p.product FROM Product p JOIN p.maker m WHERE m.maker = ?1")
	public List<String> findProductsByMaker(String maker);
	
}
