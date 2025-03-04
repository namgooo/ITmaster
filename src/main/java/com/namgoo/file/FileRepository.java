package com.namgoo.file;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer>, JpaSpecificationExecutor<File> {
	
	// 파일 목록 조회(페이징)
	public Page<File> findAll(Specification<File> specification, Pageable pageable);

	// 파일 확장자명으로 목록 조회(페이징)
	public Page<File> findByFileType(String fileType, Pageable pageable);

	// 파일 이름으로 단일 조회
	public File findByFileName(String fileName);

}
