package com.example.demo.interfaces;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Entity {
  final UUID id = UUID.randomUUID();
}
