package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.lib.XmlDatabase;
import com.example.demo.model.Building;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingQueryController {
  final XmlDatabase<Building> buildingDb = new XmlDatabase<>("building.xml", Building.class);

  @GetMapping(value = "/q/building/read/{id}", produces = "application/json")
  public Optional<Building> oneBuilding(@PathVariable String id) throws IOException {
    return buildingDb.findFirst(UUID.fromString(id));
  }

  @GetMapping(value = "/q/building/read", produces = "application/json")
  public List<Building> allBuildings() throws IOException {
    return buildingDb.findAll();
  }

  /*
   * e.g. http://localhost:8080/q/building/create?propertyType=private&
   * street=lol&number=11&commissioningDate=today&storeysNumber=5&owner=10
   */
  @GetMapping(value = "/q/building/create", produces = "application/json")
  public void addBuilding(@Valid Building newBuilding, BindingResult bindingResult, HttpServletResponse response)
      throws IOException {

    if (!bindingResult.hasErrors()) {
      buildingDb.save(newBuilding);
    }

    response.sendRedirect("/");
  }

  @GetMapping(value = "/q/building/delete/{id}", produces = "application/json")
  public void deleteBuilding(@PathVariable String id, HttpServletResponse response) throws IOException {
    buildingDb.delete(UUID.fromString(id));

    response.sendRedirect("/");
  }
}
