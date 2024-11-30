package com.spring_cloud.eureka.client.orders.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/order/{orderId}")
  public String getOrderById(@PathVariable Long orderId) {
    return orderId+" order info "+ serverPort;
  }
}
