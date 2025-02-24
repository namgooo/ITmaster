package com.namgoo.file;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer>{
	
	// 파일 목록 조회(페이징)
	public Page<File> findAll(Pageable pageable);
	
	// 파일 단일 조회
	public Optional<File> findById(Integer id);
	
	// 파일 단일 조회
	public String findByFileName(String fileName);
	
}
