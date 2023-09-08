package com.kafka.samples.consumer.repository;

import com.kafka.samples.consumer.model.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

}
