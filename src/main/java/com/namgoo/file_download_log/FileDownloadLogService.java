package com.namgoo.file_download_log;

import com.namgoo.file.File;
import com.namgoo.file.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // 파일다운로드기록 저장
    public void createFileDownloadLog(String fileName) {
        File file = this.fileRepository.findByFileName(fileName);
        FileDownloadLog fileDownloadLog = new FileDownloadLog();
        fileDownloadLog.setFile(file);
        fileDownloadLog.setCreateDate(LocalDateTime.now());
        this.fileDownloadLogRepository.save(fileDownloadLog);
    }

    // 누적 다운로드 많은 순(상위 5개) 정렬 조회
    public List<FileDownloadCountDTO> countFileDownloadLog() {
        Pageable top5 = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("fileName")));
        List<FileDownloadCountDTO> countFileDownloadLogList = this.fileDownloadLogRepository.countFileDownloadLog(top5);
        return countFileDownloadLogList;
    }

    // 실시간 다운로드 수 조회
//    public List<RealTimeDownloadCountDTO> findRealTimeDownloadCount(String startDate, String endDate) {
//        List<RealTimeDownloadCountDTO> findRealTimeDownloadCountList = this.fileDownloadLogRepository.findRealTimeDownloadCount(startDate, endDate);
//        return findRealTimeDownloadCountList;
//    }

}