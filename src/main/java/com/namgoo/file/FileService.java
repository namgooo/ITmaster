package com.namgoo.file;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Value("${file.dir}")
	private String fileDir;
	
	public Integer saveFile(MultipartFile files) throws IOException {
		if(files.isEmpty()) {
			return null;
		}
		
		// 원래 파일 이름 추출
		String originalName = files.getOriginalFilename();
		// 파일 이름으로 사용할 uuid 생성
		String uuid = UUID.randomUUID().toString();
		// 확장자 추출
		String extension = originalName.substring(originalName.lastIndexOf("."));
		// uuid와 확장자 결합
		String savedName = uuid + extension;
		// 파일을 불러올 때 사용할 파일 경로
		String savedPath = fileDir + savedName;
		// Entity 생성 후, 저장
		File file = new File();
		file.setOriginalName(originalName);
		file.setSaveName(savedName);
		file.setSavePath(savedPath);
		file.setCreateDate(LocalDateTime.now());
		// 실제 로컬에 uuid를 파일명으로 저장
		files.transferTo(new java.io.File(savedPath));
		// 데이터베이스에 파일 정보 저장
		this.fileRepository.save(file);
		
		return file.getId();
		
	}
	
	// 파일 목록
	public List<File> findFileList() {
		List<File> fileList = this.fileRepository.findAll();
		return fileList;
	}
	
	// 단일 파일 조회
	public Optional<File> file(Integer id) {
		Optional<File> file = this.fileRepository.findById(id);
		return file;
	}
	
	
	
	
	

}
