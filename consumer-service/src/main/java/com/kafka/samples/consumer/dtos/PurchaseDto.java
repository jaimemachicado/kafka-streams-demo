package com.kafka.samples.consumer.dtos;


import lombok.Data;

@Data
public class PurchaseDto {

  private long id;
  private String product;
  private int amount;
}
