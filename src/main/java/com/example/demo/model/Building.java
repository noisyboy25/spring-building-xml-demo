package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import com.example.demo.interfaces.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Building extends Entity {

  public Building(@NotBlank String propertyType, @NotBlank String street, @NotBlank String number,
      @NotBlank String commissioningDate, int storeysNumber, @NotBlank String owner) {
    this.propertyType = propertyType;
    this.street = street;
    this.number = number;
    this.commissioningDate = commissioningDate;
    this.storeysNumber = storeysNumber;
    this.owner = owner;
  }

  // Тип собственности
  @NotBlank
  String propertyType;

  // Улица
  @NotBlank
  String street;

  // Номер дома
  @NotBlank
  String number;

  // Дата сдачи в эксплуатацию
  @NotBlank
  String commissioningDate;

  // Этажность
  int storeysNumber;

  // Собственник
  @NotBlank
  String owner;
}
