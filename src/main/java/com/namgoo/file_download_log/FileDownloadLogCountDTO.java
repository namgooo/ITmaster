package com.namgoo.file_download_log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDownloadLogCountDTO {

    private String fileName;
    private Long count;

    public FileDownloadLogCountDTO(String fileName, Long count) {
        this.fileName = fileName;
        this.count = count;
    }

}
