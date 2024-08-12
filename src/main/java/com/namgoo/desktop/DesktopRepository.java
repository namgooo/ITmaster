package com.namgoo.desktop;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopRepository extends JpaRepository<Desktop, Integer>{

	// 데스크탑 목록
	public List<Desktop> findAll();
	
	// 데스크탑 단일 조회
	public Optional<Desktop> findById(Integer id);
	
	// 데스크탑명으로 단일 조회
	public Optional<Desktop> findByDesktop(String desktop);
}
