package com.namgoo.file;

import java.time.LocalDateTime;
import java.util.List;

import com.namgoo.file_category.FileCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class File {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fileName;
	private String filePath;
	private String fileType;
	private double fileSize;
	private Integer count;
	private LocalDateTime createDate;

	// ManyToOne
	@ManyToOne
	private FileCategory fileCategory;
	
}
