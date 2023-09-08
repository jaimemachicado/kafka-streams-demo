package com.kafka.samples.producer;

import com.kafka.samples.producer.model.Purchase;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@Slf4j
public class ProducerService {

  private static long purchaseId = 0;
  LinkedList<Purchase> purchases = new LinkedList<>(List.of(
      Purchase.builder().id(++purchaseId).product("Samsung").amount(1).build(),
      Purchase.builder().id(++purchaseId).product("iPhone").amount(5).build(),
      Purchase.builder().id(++purchaseId).product("Xiaomi").amount(7).build(),
      Purchase.builder().id(++purchaseId).product("POCO").amount(4).build(),
      Purchase.builder().id(++purchaseId).product("One").amount(1).build(),
      Purchase.builder().id(++purchaseId).product("Nokia").amount(2).build(),
      Purchase.builder().id(++purchaseId).product("Motorola").amount(1).build(),
      Purchase.builder().id(++purchaseId).product("Note").amount(3).build()

  ));

  public static void main(String[] args) {
    SpringApplication.run(ProducerService.class, args);
  }

  @Bean
  public Supplier<Message<Purchase>> sendPurchase() {

    return () -> {
      if (purchases.peek() != null) {
        Message<Purchase> o = MessageBuilder
            .withPayload(purchases.peek())
            .setHeader(KafkaHeaders.KEY, Objects.requireNonNull(purchases.poll()).getId())
            .build();
        log.info("Purchase: {}", o.getPayload());
        return o;
      } else {
        return null;
      }
    };
  }

}
