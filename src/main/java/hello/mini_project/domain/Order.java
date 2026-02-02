package hello.mini_project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 하나의 주문에 여러 OrderItem이 포함됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    // 편의 메서드: 주문에 상품 추가
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 주문 생성 메서드
    public static Order createOrder(OrderItem... orderItems) {
        Order order = new Order();
        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
        }
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
}