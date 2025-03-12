package com.namgoo.file_download_log;

import com.namgoo.file.File;
import com.namgoo.file.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileDownloadLogService {

    @Autowired
    private FileDownloadLogRepository fileDownloadLogRepository;
    @Autowired
    private FileRepository fileRepository;

    // 파일다운로드기록 등록
    public void createFileDownloadLog(String fileName) {
        File file = this.fileRepository.findByFileName(fileName);
        FileDownloadLog fileDownloadLog = new FileDownloadLog();
        fileDownloadLog.setFile(file);
        fileDownloadLog.setCreateDate(LocalDateTime.now());
        this.fileDownloadLogRepository.save(fileDownloadLog);
    }

}
