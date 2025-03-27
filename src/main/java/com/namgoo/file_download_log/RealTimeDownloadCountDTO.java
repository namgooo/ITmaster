package com.namgoo.file_download_log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealTimeDownloadCountDTO {

    private String fileName;
    private String downloadDate;
    private Long downloadCount;

    public RealTimeDownloadCountDTO(String fileName, String downloadDate, Long downloadCount) {
        this.fileName = fileName;
        this.downloadDate = downloadDate;
        this.downloadCount = downloadCount;
    }
}
