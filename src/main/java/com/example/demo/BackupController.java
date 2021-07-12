package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BackupController {
  final File dbFile = new File("building.xml");

  @GetMapping("/backup")
  public void fileDownload(HttpServletResponse response) throws IOException {
    response.setContentType("application/xml");
    Files.copy(dbFile.toPath(), response.getOutputStream());
  }

  @PostMapping("/backup")
  public void fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

    Files.copy(file.getInputStream(), dbFile.toPath());
  }
}
