package com.namgoo.maker;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakerRepository extends JpaRepository<Maker, Integer>{
	
	// 제조사 검색 목록(페이징)
	public Page<Maker> findAll(Specification<Maker> specification, Pageable pageable);
	
	// 제조사 목록
	public List<Maker> findAll();
	
	// 제조사 단일 조회
	public Optional<Maker> findById(Integer id);
	
	// 제조사명으로 단일 조회
	public Optional<Maker> findByMaker(String maker);
	

}
