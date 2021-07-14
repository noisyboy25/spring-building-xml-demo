package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@RestController()
public class BackupController {
  final File dbFile = new File("building.xml");

  @GetMapping("/api/backup")
  public void fileDownload(HttpServletResponse response) throws IOException {
    response.setContentType("application/xml");
    Files.copy(dbFile.toPath(), response.getOutputStream());
  }

  @PostMapping("/api/backup")
  public View fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
    Files.copy(file.getInputStream(), dbFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    return new RedirectView("/");
  }
}
