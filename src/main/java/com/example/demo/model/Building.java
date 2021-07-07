package com.example.demo.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Building {
  // Уникальный идентификатор постройки
  @NonNull
  public String id;

  // Тип собственности
  @NonNull
  String propertyType = "private";

  // Улица
  @NonNull
  String street;

  // Номер дома
  @NonNull
  String number;

  // Дата сдачи в эксплуатацию
  String commissioningDate;

  // Этажность
  int storeysNumber = 1;

  // Собственник
  String owner;
}
