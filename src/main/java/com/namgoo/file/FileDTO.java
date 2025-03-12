package com.namgoo.file;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileDTO {
    private Integer id;
    private String fileName;
    private String filePath;
    private String fileType;
    private double fileSize;
    private LocalDateTime createDate;
    private Integer count;
    private String fileCategory;

    public FileDTO(Integer id, String fileName, String filePath, String fileType, double fileSize, LocalDateTime createDate, Integer fileDownloadCount, String fileCategory) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.createDate = createDate;
        this.count = fileDownloadCount;
        this.fileCategory = fileCategory;
    }
}
