package com.namgoo.product_info;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>, JpaSpecificationExecutor<ProductInfo> {
		
	// 제품 정보 검색 목록(페이징)
	public Page<ProductInfo> findAll(Specification<ProductInfo> specification, Pageable pageable);
	
	// 제품 정보 목록
	public List<ProductInfo> findAll();
	
	// 제품 정보 단일 조회
	public Optional<ProductInfo> findById(Integer id);
	
	// 제품 정보 카테고리, '키보드' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c ON p.category_id = c.id WHERE c.category = '키보드'", nativeQuery = true)
	public Integer countProductInfoKeyboard();
	
	// 제품 정보 카테고리, '마우스' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c ON p.category_id = c.id WHERE c.category = '마우스'", nativeQuery = true)
	public Integer countProductInfoMouse();
	
	// 제품 정보 카테고리, '모니터' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c ON p.category_id = c.id WHERE c.category = '모니터'", nativeQuery = true)
	public Integer countProductInfoMonitor();
	
	// 제품 정보(키보드) 상태 - '정상' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '정상' AND c.category = '키보드'", nativeQuery = true)
	public Integer countProductInfoKeyboardNormal();
	// 제품 정보(키보드) 상태 - '결함' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '결함' AND c.category = '키보드'", nativeQuery = true)
	public Integer countProductInfoKeyboardDefective();
	// 제품 정보(키보드) 상태 - '고장' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '고장' AND c.category = '키보드'", nativeQuery = true)
	public Integer countProductInfoKeyboardFaulty();
	
	// 제품 정보(마우스) 상태 - '정상' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '정상' AND c.category = '마우스'", nativeQuery = true)
	public Integer countProductInfoMouseNormal();
	// 제품 정보(마우스) 상태 - '결함' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '결함' AND c.category = '마우스'", nativeQuery = true)
	public Integer countProductInfoMouseDefective();
	// 제품 정보(마우스) 상태 - '고장' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '고장' AND c.category = '마우스'", nativeQuery = true)
	public Integer countProductInfoMouseFaulty();
	
	// 제품 정보(모니터) 상태 - '정상' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '정상' AND c.category = '모니터'", nativeQuery = true)
	public Integer countProductInfoMonitorNormal();
	// 제품 정보(모니터) 상태 - '결함' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '결함' AND c.category = '모니터'", nativeQuery = true)
	public Integer countProductInfoMonitorDefective();
	// 제품 정보(모니터) 상태 - '고장' 총합 조회
	@Query(value = "SELECT COUNT(*) FROM product_info p JOIN category c on p.category_id = c.id WHERE p.item_status = '고장' AND c.category = '모니터'", nativeQuery = true)
	public Integer countProductInfoMonitorFaulty();
}
