package com.namgoo.file;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	private static final String UPLOAD_DIR = "C:/files/";
	
	// 파일 목록 조회
	@GetMapping("/upload")
	public String findFileList(Model model) {
		List<File> fileList = this.fileService.findFileList();
		model.addAttribute("fileList", fileList);
		return "main/file";
	}
	
	// 파일 업로드
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		this.fileService.uploadFile(file);
		return "redirect:/file/upload";
	}
	
	// 파일 다운로드
	@GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam(name="fileId") Long fileId) {
        try {
            Resource resource = fileService.downloadFile(fileId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
	
	
}
