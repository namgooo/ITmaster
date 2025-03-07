package com.namgoo.file_category;

import com.namgoo.file.File;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class FileCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileCategory;
    private LocalDateTime createDate;

    // OneToMany
    @OneToMany(mappedBy = "fileCategory", cascade = CascadeType.REMOVE)
    private List<File> fileList;

}
