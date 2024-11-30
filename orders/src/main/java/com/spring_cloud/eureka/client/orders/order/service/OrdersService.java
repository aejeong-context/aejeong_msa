package com.spring_cloud.eureka.client.orders.order.service;

import com.spring_cloud.eureka.client.orders.order.model.Orders;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

  private final Logger log = LoggerFactory.getLogger(getClass());
  private final CircuitBreakerRegistry circuitBreakerRegistry;

  @PostConstruct
  public void registerEventListener(){
    circuitBreakerRegistry.circuitBreaker("ordersService").getEventPublisher()
        // 연속 실패로 인해 브레이커가 오픈 상태로 전환
        .onStateTransition(event -> log.info("Circuit breaker state transition: {}", event.getStateTransition()))
        // 설정한 실패율 임계치 초과
        .onFailureRateExceeded(event -> log.info("Circuit breaker failure rate exceeded: {}", event.getFailureRate()))
        // 브레이커가 오픈 상태일 때 호출이 차단될 경우
        .onCallNotPermitted(event -> log.info("Circuit breaker call not permitted: {}", event.getEventType()))
        // 브레이커 내부에서 호출 실패
        .onError(event -> log.info("Circuit breaker error: {}", event.getEventType()));
  }

  @CircuitBreaker(name = "ordersService", fallbackMethod = "fallbackOrderDetail")
  public Orders getOrder(Long serviceId, Long orderId) {
    log.info("get order detail serviceId: {}, orderId: {}", serviceId, orderId);
    if (orderId == 2) {
      log.warn("empty body for order id 2");
      throw new RuntimeException("Empty response body");
    }
    return new Orders(orderId, serviceId, 100L);
  }

  public Orders fallbackOrderDetail(Long serviceId, Long orderId, Throwable throwable) {
    log.warn("fallback order detail serviceId: {}, orderId: {}", serviceId, orderId);
    return new Orders(orderId, serviceId, 0L);
  }
}
