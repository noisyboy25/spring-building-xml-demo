package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.lib.XmlDatabase;
import com.example.demo.model.Building;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {
  final XmlDatabase<Building> buildingDb = new XmlDatabase<>("building.xml", Building.class);

  @GetMapping(value = "/api/building/{id}", produces = "application/json")
  public Optional<Building> oneBuilding(@PathVariable String id) throws IOException {
    return buildingDb.findFirst(UUID.fromString(id));
  }

  @GetMapping(value = "/api/building", produces = "application/json")
  public List<Building> allBuildings() throws IOException {
    return buildingDb.findAll();
  }

  @PostMapping(value = "/api/building", produces = "application/json")
  public Building addBuilding(@RequestBody Building newBuilding, BindingResult bindingResult) throws IOException {
    Building result = null;

    if (!bindingResult.hasErrors()) {
      result = buildingDb.save(newBuilding);
    }

    return result;
  }

  @DeleteMapping(value = "/api/building/{id}", produces = "application/json")
  public void deleteBuilding(@PathVariable String id) throws IOException {
    buildingDb.delete(UUID.fromString(id));
  }
}
