package com.kafka.samples.producer;

import com.kafka.samples.producer.model.Purchase;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
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

  private static final int MAX_NUMBER_GENERATED = 500;

  LinkedList<Purchase> purchases = generatePurchases(MAX_NUMBER_GENERATED);

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
        log.info("Sent purchase: {}", o.getPayload());
        return o;
      } else {
        return null;
      }
    };
  }


  public static LinkedList<Purchase> generatePurchases(int numberOfPurchases) {
    LinkedList<Purchase> purchases = new LinkedList<>();
    Random random = new Random();
    String[] manufacturers = {"Samsung", "iPhone", "Xiaomi", "POCO", "One", "Nokia", "Motorola",
        "Redmi"};

    for (int i = 0; i < numberOfPurchases; i++) {
      int randomIndex = random.nextInt(manufacturers.length);
      String randomManufacturer = manufacturers[randomIndex];
      Purchase purchase = Purchase.builder()
          .id(++purchaseId)
          .product(randomManufacturer)
          .amount(1)
          .build();
      purchases.add(purchase);
    }

    return purchases;
  }

}
