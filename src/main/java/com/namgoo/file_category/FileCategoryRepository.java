package com.namgoo.file_category;

import com.namgoo.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileCategoryRepository extends JpaRepository<FileCategory, Integer> {

    // 파일카테고리 목록 조회
    public List<FileCategory> findAll();

    // 파일카테고리 파일카테고리명으로 단일 조회
    public FileCategory findByFileCategory(String fileCategory);

}
