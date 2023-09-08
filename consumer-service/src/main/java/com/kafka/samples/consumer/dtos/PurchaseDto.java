package com.kafka.samples.consumer.dtos;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseDto {

  private long id;
  private String product;
  private int amount;
}
