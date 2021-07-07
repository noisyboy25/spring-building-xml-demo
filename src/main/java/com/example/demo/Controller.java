package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class Controller {
  final File dbFile = new File("building.xml");

  @GetMapping(value = "/building/{id}", produces = "application/json")
  public Optional<Building> oneBuilding(@PathVariable String id) {
    var xmlMapper = new XmlMapper();

    List<Building> dataBase = new ArrayList<>();
    Optional<Building> building = Optional.empty();

    try {
      dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
      });
      building = dataBase.stream().filter(b -> b.getId().equals(id)).findFirst();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return building;
  }

  @GetMapping(value = "/building", produces = "application/json")
  public List<Building> allBuildings() {
    var xmlMapper = new XmlMapper();

    List<Building> dataBase = new ArrayList<>();

    try {
      dataBase = (List<Building>) xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    return dataBase;
  }

  @PostMapping(value = "/building", produces = "application/json")
  public Building addBuilding(@RequestBody Building newBuilding) {

    var xmlMapper = new XmlMapper();

    List<Building> dataBase = new ArrayList<>();

    try {
      dataBase = (List<Building>) xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    dataBase.add(newBuilding);

    try {
      xmlMapper.writeValue(dbFile, dataBase);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return newBuilding;
  }

  @DeleteMapping(value = "/building/{id}", produces = "application/json")
  public void deleteBuilding(@PathVariable String id) {

    var xmlMapper = new XmlMapper();

    List<Building> dataBase = new ArrayList<>();

    try {
      dataBase = (List<Building>) xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    dataBase.removeIf(b -> b.getId().equals(id));

    try {
      xmlMapper.writeValue(dbFile, dataBase);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
