package com.spring_cloud.eureka.client.orders.order.controller;

import com.spring_cloud.eureka.client.orders.order.model.Orders;
import com.spring_cloud.eureka.client.orders.order.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrdersService ordersService;

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/order/{orderId}")
  public String getOrderById(@PathVariable Long orderId) {
    return orderId + " order info " + serverPort;
  }

  @GetMapping("/{serviceId}/order/{orderId}/detail")
  public Orders getOrder(@PathVariable Long serviceId, @PathVariable Long orderId) {
    return ordersService.getOrder(serviceId,orderId);
  }
}
