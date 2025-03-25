package com.namgoo.file_download_log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealTimeDownloadCountDTO {

    private Integer hour;
    private Long downloadCount;
    private String fileName;

    public RealTimeDownloadCountDTO(Integer hour, Long downloadCount, String fileName) {
        this.hour = hour;
        this.downloadCount = downloadCount;
        this.fileName = fileName;
    }
}
