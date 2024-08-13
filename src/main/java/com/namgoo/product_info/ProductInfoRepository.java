package com.namgoo.product_info;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>{
	
	// 제품 정보 목록
	public List<ProductInfo> findAll();
	
	// 제품 정보 단일 조회
	public Optional<ProductInfo> findById(Integer id);

}
