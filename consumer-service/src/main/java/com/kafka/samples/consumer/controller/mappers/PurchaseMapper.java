package com.kafka.samples.consumer.controller.mappers;

import com.kafka.samples.consumer.dtos.PurchaseDto;
import com.kafka.samples.consumer.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

  PurchaseDto purchaseToPurchaseDto(Purchase purchase);

  Purchase purchaseDtoToPurchase(PurchaseDto v);
}
