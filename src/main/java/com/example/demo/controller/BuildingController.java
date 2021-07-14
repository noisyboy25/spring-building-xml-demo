package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Building;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {
  final File dbFile = new File("building.xml");

  @GetMapping(value = "/building/{id}", produces = "application/json")
  public Optional<Building> oneBuilding(@PathVariable String id) throws IOException {
    var xmlMapper = new XmlMapper();

    List<Building> dataBase;

    dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });

    return dataBase.stream().filter(b -> b.getId().equals(id)).findFirst();
  }

  @GetMapping(value = "/building", produces = "application/json")
  public List<Building> allBuildings() throws IOException {
    var xmlMapper = new XmlMapper();

    return xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });
  }

  @PostMapping(value = "/building", produces = "application/json")
  public Building addBuilding(@RequestBody Building newBuilding) throws IOException {

    var xmlMapper = new XmlMapper();

    List<Building> dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });

    dataBase.add(newBuilding);

    xmlMapper.writeValue(dbFile, dataBase);

    return newBuilding;
  }

  @DeleteMapping(value = "/building/{id}", produces = "application/json")
  public void deleteBuilding(@PathVariable String id) throws IOException {

    var xmlMapper = new XmlMapper();

    List<Building> dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });

    dataBase.removeIf(b -> b.getId().equals(id));

    xmlMapper.writeValue(dbFile, dataBase);
  }
}
