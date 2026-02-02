package hello.mini_project.dto;

import hello.mini_project.domain.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


import java.time.LocalDateTime;

public class OrderDto {
    public record Request(
            Long productId,
            Integer count){}

    @Builder
    public record Response(Long orderId, String productName, Integer orderPrice, Integer count, LocalDateTime orderDate){
        public static Response from(Order order) {
            // 주문 상품 리스트 중 첫 번째 항목 추출 - 현재 과제에서는 주문시 상품을 1개만 선택하므로
            var orderItem = order.getOrderItems().get(0);

            return Response.builder()
                    .orderId(order.getId())
                    .productName(orderItem.getProduct().getName()) // 여기서 실시간 참조로 이름 조회
                    .orderPrice(orderItem.getOrderPrice())
                    .count(orderItem.getCount())
                    .orderDate(order.getOrderDate())
                    .build();
        }
    }
}
