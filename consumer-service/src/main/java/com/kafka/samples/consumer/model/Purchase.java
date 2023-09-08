package com.kafka.samples.consumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "purchases")
@Data
public class Purchase {

  @Id
  @GeneratedValue
  private long id;
  private String product;
  private int amount;
}
