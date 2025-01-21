package com.namgoo.desktop_type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopTypeRepository extends JpaRepository<DesktopType, Integer> {
	
	// 데스크탑타입 목록 조회
	public List<DesktopType> findAll();
	
	// 데스크탑타입 단일 조회
	public Optional<DesktopType> findByType(String type);

}
