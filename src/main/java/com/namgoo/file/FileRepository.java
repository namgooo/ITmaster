package com.namgoo.file;

import com.namgoo.file_category.FileCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<File, Integer>, JpaSpecificationExecutor<File> {
	
	// 파일 목록 조회(페이징)
	public Page<File> findAll(Specification<File> specification, Pageable pageable);

	// 파일카테고리 별 파일 목록 검색 조회(페이징)
	@Query("SELECT f FROM File f WHERE f.fileCategory = :fileCategory AND f.fileName LIKE %:keyword%")
	public Page<File> findByFileCategoryAndFile(@Param("fileCategory") FileCategory fileCategory, @Param("keyword") String keyword, Pageable pageable);

	// 파일 이름으로 단일 조회
	public File findByFileName(String fileName);



}
