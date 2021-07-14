package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Building;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingQueryController {
  final File dbFile = new File("building.xml");

  @GetMapping(value = "/q/building/read/{id}", produces = "application/json")
  public Optional<Building> oneBuilding(@PathVariable String id) throws IOException {
    var xmlMapper = new XmlMapper();

    List<Building> dataBase;

    dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });

    return dataBase.stream().filter(b -> b.getId().equals(id)).findFirst();
  }

  @GetMapping(value = "/q/building/read", produces = "application/json")
  public List<Building> allBuildings() throws IOException {
    var xmlMapper = new XmlMapper();

    return xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });
  }

  // e.g.
  // http://localhost:8080/q/building/create?id=10020202&propertyType=private&street=lol&number=11&commissioningDate=today&storeysNumber=5&owner=10
  @GetMapping(value = "/q/building/create", produces = "application/json")
  public Building addBuilding(Building newBuilding) throws IOException {

    var xmlMapper = new XmlMapper();

    List<Building> dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });

    dataBase.add(newBuilding);

    xmlMapper.writeValue(dbFile, dataBase);

    return newBuilding;
  }

  @GetMapping(value = "/q/building/delete/{id}", produces = "application/json")
  public void deleteBuilding(@PathVariable String id) throws IOException {

    var xmlMapper = new XmlMapper();

    List<Building> dataBase = xmlMapper.readValue(dbFile, new TypeReference<List<Building>>() {
    });

    dataBase.removeIf(b -> b.getId().equals(id));

    xmlMapper.writeValue(dbFile, dataBase);
  }
}
