package com.namgoo.file_download_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fileDownloadLog")
public class FileDownloadLogController {

    @Autowired
    private FileDownloadLogService fileDownloadLogService;

}
