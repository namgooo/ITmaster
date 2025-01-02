package com.namgoo.product_info;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>, JpaSpecificationExecutor<ProductInfo> {
		
	// 제품 정보 검색 목록(페이징)
	public Page<ProductInfo> findAll(Specification<ProductInfo> specification, Pageable pageable);
	
	// 제품 정보 목록
	public List<ProductInfo> findAll();
	 
	// 제품 정보 단일 조회
	public Optional<ProductInfo> findById(Integer id);
	
	
			
}
