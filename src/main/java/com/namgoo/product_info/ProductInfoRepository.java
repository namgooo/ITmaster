package com.namgoo.product_info;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>{
	
	// 제품 정보 목록
//	@Query(
//			"select " 
//			+ "pi.simpleName " 
//		    + "pi.useStatus " 
//		    + "pi.itemStatus " 
//		    + "pi.location " 
//		    + "pi.price "
//		    + "pi.buyYear "
//		    + "pi.uniqueCode " 
//		    + "pi.createDate " 
//		    + "c.category " 
//		    + "m.maker " 
//		    + "p.product " 
//		    + "d.department "
//		    + "e.employee "
//		    + "from ProductInfo pi "
//		    + "inner join Category c on pi.category = c "
//		    + "inner join Maker m on pi.maker = m "
//		    + "inner join Product p on pi.product = p "
//		    + "inner join Department d on pi.department = d "
//		    + "inner join Employee e on pi.employee = e "
//		    + "where "
//			+ "pi.simpleName like %:keyword% "
//		    + "or pi.useStatus like %:keyword% "
//		    + "or pi.itemStatus like %:keyword% "
//		    + "or pi.location like %:keyword% "
//		    + "or pi.price like %:keyword% "
//		    + "or pi.buyYear like %:keyword% "
//		    + "or pi.uniqueCode like %:keyword% "
//		    + "or pi.createDate like %:keyword% "
//		    + "or c.category like %:keyword% "
//		    + "or m.maker like %:keyword% "
//		    + "or p.product like %:keyword% "
//		    + "or d.department like %:keyword% "
//		    + "or e.employee like %:keyword% "
//			)
	public Page<ProductInfo> findAll(Pageable pageable);
	
	// 제품 정보 단일 조회
	public Optional<ProductInfo> findById(Integer id);
	
    @Query(
            "select pi " +
            "from ProductInfo pi " +
            "inner join pi.category c " + 
            "inner join pi.maker m " + 
            "inner join pi.product p " + 
            "inner join pi.department d " + 
            "inner join pi.employee e " + 
            "where " +
            "pi.simpleName like %:keyword% " +
            "or pi.useStatus like %:keyword% " +
            "or pi.itemStatus like %:keyword% " +
            "or pi.location like %:keyword% " +
            "or pi.price like %:keyword% " +
            "or pi.buyYear like %:keyword% " +
            "or pi.uniqueCode like %:keyword% " +
            "or c.category like %:keyword% " +
            "or m.maker like %:keyword% " +
            "or p.product like %:keyword% " +
            "or d.department like %:keyword% " +
            "or e.employee like %:keyword% "
        )
	public Page<ProductInfo> findAllByKeyword(Pageable pageable, @Param("keyword") String keyword);
	
}
