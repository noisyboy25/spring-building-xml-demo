package com.example.demo;

import java.util.Date;

import com.example.demo.model.Building;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @GetMapping("/test")
  public Building all() {
    var data = new Building("1294182030901", "Random st.", "1");
    data.setCommissioningDate(new Date().toString());
    return data;
  }
}
