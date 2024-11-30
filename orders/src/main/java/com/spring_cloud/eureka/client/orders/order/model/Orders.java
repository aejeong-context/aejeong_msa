package com.spring_cloud.eureka.client.orders.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

  private Long id;
  private Long serviceId;
  private Long amount;
}
