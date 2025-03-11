package com.namgoo.file_category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileCategoryCountDTO {
    private String fileCategory;
    private Long total;

    public FileCategoryCountDTO(String fileCategory, Long total) {
        this.fileCategory = fileCategory;
        this.total = total;
    }

}
