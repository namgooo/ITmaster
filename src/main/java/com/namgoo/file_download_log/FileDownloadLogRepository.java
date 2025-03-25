package com.namgoo.file_download_log;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileDownloadLogRepository extends JpaRepository<FileDownloadLog, Integer> {

    // 파일 단일 누적 다운로드 수 조회
    @Query("SELECT COUNT(f) FROM FileDownloadLog f WHERE f.file.id = :fileId")
    Integer countDownloadsByFileId(@Param("fileId") Integer fileId);

    // 누적 다운로드 많은 순 정렬 조회
    @Query("SELECT new com.namgoo.file_download_log.FileDownloadCountDTO(f.fileName, COUNT(fdl.id)) " +
            "FROM File f " +
            "JOIN f.fileDownloadLogList fdl " +
            "GROUP BY f.fileName " +
            "ORDER BY COUNT(fdl.id) DESC")
    List<FileDownloadCountDTO> countFileDownloadLog(Pageable pageable);

    // 실시간 다운로드 수(최근 24시간)
    @Query("SELECT new com.namgoo.file_download_log.RealTimeDownloadCountDTO(HOUR(fdl.createDate), COUNT(fdl.id), f.fileName) " +
           "FROM FileDownloadLog fdl " +
           "JOIN fdl.file f " +
           "WHERE fdl.createDate >= :startDate " +
           "AND fdl.createDate <= :endDate " +
           "GROUP BY HOUR(fdl.createDate), f.fileName " +
           "ORDER BY HOUR(fdl.createDate), f.fileName")
    List<RealTimeDownloadCountDTO> findRealTimeDownloadCount(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
