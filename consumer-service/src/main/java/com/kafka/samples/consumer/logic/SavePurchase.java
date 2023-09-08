package com.kafka.samples.consumer.logic;

import com.kafka.samples.consumer.controller.mappers.PurchaseMapper;
import com.kafka.samples.consumer.dtos.PurchaseDto;
import com.kafka.samples.consumer.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePurchase {

  private static final Logger log = LoggerFactory.getLogger(SavePurchase.class);
  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  PurchaseMapper purchaseMapper;

  public SavePurchase(PurchaseRepository repository) {
    this.purchaseRepository = repository;
  }

  public void save(PurchaseDto purchaseDto) {
    purchaseRepository.save(purchaseMapper.purchaseDtoToPurchase(purchaseDto));
    log.info("Saved purchase with id={}, product={}, amount={}", purchaseDto.getId(),
        purchaseDto.getProduct(), purchaseDto.getAmount());
  }
}
