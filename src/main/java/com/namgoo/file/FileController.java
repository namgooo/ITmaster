package com.namgoo.file;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	// 파일 저장 경로
	private static final String UPLOAD_DIR = "C:/files/";
	
	// 파일 목록 조회
	@GetMapping("/list")
	public String findFileList(Model model) {
		List<File> fileList = this.fileService.findFileList();
		model.addAttribute("fileList", fileList);
		return "admin/file_list";
	}

	// 파일 업로드
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		this.fileService.uploadFile(file);
		return "redirect:/file/list";
	}
	
	// 파일 다운로드
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) throws IOException {
		// 한글 이름 파일 인코딩
		// HTTP 헤더는 기본적으로 ASCII 문자만 처리하기 때문에 파일명에 한글이 포함될 때 Base64 형식으로 인코딩해야 함
		String encodedFileName = "=?UTF-8?B?" + Base64.getEncoder().encodeToString(fileName.getBytes(StandardCharsets.UTF_8)) + "?=";
	
		// 서비스 호출
		Resource file = this.fileService.downloadFile(fileName);

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM) // 응답 타입을 설정(파일 다운로드)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"") // 브라우저는 해당 값을 보고 파일 다운로드 창을 띄움
				.body(file); // 파일 내용을 사용자에게 전송
	}

	// 2025-02-21 파일 업로드 및 다운로드
}
