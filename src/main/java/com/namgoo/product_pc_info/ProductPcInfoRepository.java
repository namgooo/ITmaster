package com.namgoo.product_pc_info;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPcInfoRepository extends JpaRepository<ProductPcInfo, Integer>{

	// 제품 정보(PC) 목록
	public List<ProductPcInfo> findAll();
	
	// 제품 정보(PC) 단일 조회
	public Optional<ProductPcInfo> findById(Integer id);
	
}
