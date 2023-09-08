package com.kafka.samples.consumer;

import com.kafka.samples.consumer.dtos.PurchaseDto;
import com.kafka.samples.consumer.services.SavePurchase;
import java.util.function.Consumer;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerService {

  private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

  public static void main(String[] args) {
    SpringApplication.run(ConsumerService.class, args);
  }

  @Autowired
  SavePurchase savePurchase;

  @Bean
  public Consumer<KStream<Long, PurchaseDto>> processPurchase() {
    return (purchase) -> purchase
        .peek((k, v) -> {
          //log.info("New purchase: key {}, value {}", k, v);
          savePurchase.save(v);
        });
  }


}
