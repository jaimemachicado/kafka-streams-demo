package com.kafka.samples.consumer.controller;

import com.kafka.samples.consumer.controller.mappers.PurchaseMapper;
import com.kafka.samples.consumer.dtos.PurchaseDto;
import com.kafka.samples.consumer.repository.PurchaseRepository;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

  @Autowired
  public final PurchaseRepository purchaseRepository;

  @Autowired
  public final PurchaseMapper purchaseMapper;

  @GetMapping(value = "/purchase/all")
  public ResponseEntity<List<PurchaseDto>> getAllPurchases() {
    LinkedList<PurchaseDto> purchasesDtos = new LinkedList<>();
    purchaseRepository.findAll().forEach(purchase -> {
      purchasesDtos.add(purchaseMapper.purchaseToPurchaseDto(purchase));
    });
    return ResponseEntity.ok(purchasesDtos);
  }
}
