package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Building {
  // Уникальный идентификатор постройки
  String id;

  // Тип собственности
  String propertyType = "private";

  // Улица
  String street;

  // Номер дома
  String number;

  // Дата сдачи в эксплуатацию
  String commissioningDate;

  // Этажность
  int storeysNumber = 1;

  // Собственник
  String owner;
}
