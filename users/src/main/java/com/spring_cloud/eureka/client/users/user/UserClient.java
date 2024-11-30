package com.spring_cloud.eureka.client.users.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orders")
public interface UserClient {

  @GetMapping("/order/{orderId}")
  String getOrderById(@PathVariable("orderId") Long orderId);
}
