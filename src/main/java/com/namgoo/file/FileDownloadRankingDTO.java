package com.namgoo.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDownloadRankingDTO {
    
    private String fileName;
    private Integer count;
    
    public FileDownloadRankingDTO(String 파일이름, Integer 누적다운로드수) {
        this.fileName = 파일이름;
        this.count = 누적다운로드수;
    }

}
