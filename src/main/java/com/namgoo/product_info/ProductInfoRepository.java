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
	
	// 카테고리 별 제품 정보 총합 조회
	@Query("SELECT new com.namgoo.product_info.ProductInfoCategoryAllDTO(" +
		   "c.category AS 카테고리, COUNT(c.category)) AS 총합 " +	
		   "FROM ProductInfo AS pi " +
		   "JOIN Category AS c ON pi.category.id = c.id " +
		   "WHERE c.category IN ('모니터', '키보드', '마우스') " +
		   "GROUP BY c.category")
	public List<ProductInfoCategoryAllDTO> countCategoryProductInfoList();
	
	// 카테고리, 상태 별 전산 물품 총합 조회
	@Query("SELECT new com.namgoo.product_info.ProductInfoCategoryAndItemStatusAllDTO(" +
		   "c.category AS 카테고리, " +
		   "COUNT(CASE WHEN pi.itemStatus = '정상' THEN 1 END) AS 정상, " +
		   "COUNT(CASE WHEN pi.itemStatus = '결함' THEN 1 END) AS 결함, " +
		   "COUNT(CASE WHEN pi.itemStatus = '고장' THEN 1 END)) AS 고장 " +
		   "FROM ProductInfo AS pi " +
		   "JOIN Category AS c ON pi.category.id = c.id " +
		   "WHERE c.category IN ('모니터', '키보드', '마우스') " +
		   "AND pi.itemStatus IN ('정상', '결함', '고장') " +
		   "GROUP BY c.category")
	public List<ProductInfoCategoryAndItemStatusAllDTO> countCategoryAndItemStatusProductInfoList();
	
}