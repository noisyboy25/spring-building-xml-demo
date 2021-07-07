package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.example.demo.model.Building;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @GetMapping(value = "/building", produces = "application/json")
  public Building allBuildings() {
    var xmlMapper = new XmlMapper();

    Building data = null;

    try {
      data = xmlMapper.readValue(new File("building.xml"), Building.class);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return data;
  }

  @PostMapping(value = "/building", produces = "application/json")
  public Building addBuilding(@RequestBody Building newBuilding) {
    var xmlMapper = new XmlMapper();

    try {
      xmlMapper.writeValue(new File("building.xml"), newBuilding);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return newBuilding;
  }
}
