package com.namgoo.file_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileCategoryService {

    @Autowired
    private FileCategoryRepository fileCategoryRepository;

    // 파일카테고리 목록 조회
    public List<FileCategory> findFileCategoryList() {
        List<FileCategory> fileCategoryList = this.fileCategoryRepository.findAll();
        return fileCategoryList;
    }

    // 파일카테고리 등록
    public void createFileCategory(FileCategoryDTO dto) {
        FileCategory fileCategory = new FileCategory();
        fileCategory.setFileCategory(dto.getFileCategory());
        fileCategory.setCreateDate(LocalDateTime.now());
        this.fileCategoryRepository.save(fileCategory);
    }

    // 파일카테고리 ID로 단일 조회
    public FileCategory findById(Integer id) {
        FileCategory fileCategory = this.fileCategoryRepository.findById(id).orElse(null);
        return fileCategory;
    }

    // 파일카테고리 별, 파일 총합 조회
    public List<FileCategoryCountDTO> findFileCategoryCountList() {
        List<FileCategoryCountDTO> fileCategoryCountList = this.fileCategoryRepository.findFileCategoryCount();
        return fileCategoryCountList;
    }

}
