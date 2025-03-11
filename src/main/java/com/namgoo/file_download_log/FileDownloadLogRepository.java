package com.namgoo.file_download_log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDownloadLogRepository extends JpaRepository<FileDownloadLog, Integer> {

    // 파일다운로드기록 조회
    @Query("SELECT new com.namgoo.file_download_log.FileDownloadLogCountDTO( " +
           "f.fileName, COUNT(fdl.id)) " +
           "FROM File AS f " +
           "JOIN FileDownloadLog AS fdl ON fdl.file.id = f.id " +
           "GROUP BY f.id, f.fileName")
    public List<FileDownloadLogCountDTO> fileDownloadLogCount();

}
