package com.namgoo.file_download_log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDownloadCountDTO {

    private String fileName;
    private Long downloadCount;

    public FileDownloadCountDTO(String fileName, Long downloadCount) {
        this.fileName = fileName;
        this.downloadCount = downloadCount;
    }

}
