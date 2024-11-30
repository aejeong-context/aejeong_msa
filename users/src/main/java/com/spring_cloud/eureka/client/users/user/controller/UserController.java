package com.spring_cloud.eureka.client.users.user.controller;

import com.spring_cloud.eureka.client.users.user.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  public UserController(UserClient userClient) {
    this.userClient = userClient;
  }

  private final UserClient userClient;

  @GetMapping("/user/review")
  public String getUser(@RequestParam("orderId") Long orderId) {
    return userClient.getOrderById(orderId);
  }
}
