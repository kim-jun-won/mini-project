package hello.mini_project.domain;

import hello.mini_project.domain.Order;
import hello.mini_project.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 주문한 상품 참조

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 연결된 주문 참조

    private Integer orderPrice; // 주문 당시의 가격 (기록용)
    private Integer count;      // 주문 수량

    // 생성 메서드
    public static OrderItem createOrderItem(Product product, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderPrice(product.getPrice()); // 현재 상품 가격을 복사
        orderItem.setCount(count);
        return orderItem;
    }
}