package com.namgoo.file_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileCategoryRepository extends JpaRepository<FileCategory, Integer> {

    // 파일카테고리 목록 조회
    public List<FileCategory> findAll();

    // 파일카테고리 파일카테고리명으로 단일 조회
    public FileCategory findByFileCategory(String fileCategory);

    // 파일카테고리 ID로 단일 조회
    public Optional<FileCategory> findById(Integer id);

    // 파일카테고리 별, 파일 총합 조회
    @Query("SELECT new com.namgoo.file_category.FileCategoryCountDTO(" +
            "fc.fileCategory, COUNT(f.fileCategory)) " +
            "FROM File AS f " +
            "JOIN FileCategory AS fc ON f.fileCategory.id = fc.id " +
            "GROUP BY fc.fileCategory")
    public List<FileCategoryCountDTO> findFileCategoryCount();

}
