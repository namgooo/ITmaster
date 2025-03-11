package com.namgoo.file_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/file-category")
public class FileCategoryController {

    @Autowired
    private FileCategoryService fileCategoryService;

    // 파일카테고리 목록 조회
    @GetMapping("/list")
    public String findFileCategoryList(Model model) {
        List<FileCategory> fileCategoryList = this.fileCategoryService.findFileCategoryList();
        model.addAttribute("fileCategoryList", fileCategoryList);
        return "admin/file_category";
    }

    // 파일카테고리 등록
    @PostMapping("/create")
    public String createFileCategory(FileCategoryDTO dto) {
        this.fileCategoryService.createFileCategory(dto);
        return "redirect:/file-category/list";
    }


}
