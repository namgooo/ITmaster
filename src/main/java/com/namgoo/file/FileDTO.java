package com.namgoo.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
    private String fileName;
    private String filePath;
    private String fileType;
    private double fileSize;
    private Integer count;
}
