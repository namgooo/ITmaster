package com.namgoo.file_download_log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDownloadLogRepository extends JpaRepository<FileDownloadLog, Integer> {

    // 파일 별 누적 다운로드 수 조회 
    @Query("SELECT COUNT(f) FROM FileDownloadLog f WHERE f.file.id = :fileId")
    Integer countDownloadsByFileId(@Param("fileId") Integer fileId);

}
