package com.example.demo.lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.interfaces.Entity;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlDatabase<T extends Entity> {
  private final XmlMapper xmlMapper = new XmlMapper();
  private final File dbFile;

  private JavaType type;

  public XmlDatabase(String filePath, Class<T> model) {
    this.dbFile = new File(filePath);
    type = xmlMapper.getTypeFactory().constructParametricType(List.class, model);
  }

  private void createFile() throws IOException {
    List<T> empty = new ArrayList<>();

    xmlMapper.writeValue(dbFile, empty);
  }

  public Optional<T> findFirst(UUID id) throws IOException {
    Optional<T> result = Optional.empty();
    List<T> rows;
    try {
      rows = xmlMapper.readValue(this.dbFile, type);
      return rows.stream().filter(b -> b.getId().equals(id)).findFirst();
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }
    return result;
  }

  public List<T> findAll() throws IOException {
    List<T> result = new ArrayList<>();

    try {
      List<T> rows = xmlMapper.readValue(this.dbFile, type);
      result = rows;
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }

    return result;
  }

  public T save(T entity) throws IOException {
    try {
      List<T> rows = xmlMapper.readValue(this.dbFile, type);

      rows.add(entity);

      xmlMapper.writeValue(dbFile, rows);
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }

    return entity;
  }

  public void delete(UUID id) throws IOException {
    try {
      List<T> rows = xmlMapper.readValue(this.dbFile, type);

      rows.removeIf(b -> b.getId().equals(id));

      xmlMapper.writeValue(dbFile, rows);
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }
  }
}
