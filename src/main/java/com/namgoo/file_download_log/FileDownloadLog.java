package com.namgoo.file_download_log;

import com.namgoo.file.File;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FileDownloadLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createDate;

    // ManyToOne
    @ManyToOne
    private File file;

}
