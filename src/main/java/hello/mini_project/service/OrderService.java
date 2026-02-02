package hello.mini_project.service;

import hello.mini_project.domain.Order;
import hello.mini_project.domain.OrderItem;
import hello.mini_project.domain.Product;
import hello.mini_project.dto.OrderDto;
import hello.mini_project.repository.OrderRepository;
import hello.mini_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long createorder(Long productId, int count) {
        // 1. 엔티티 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        // 2. 주문 재고 차감
        product.removeStock(count);

        // 3. 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, count);

        // 4. 주문 생성
        Order order = Order.createOrder(orderItem);

        // 5. 주문 저장 (CascadeType.ALL 설정 덕분에 orderItem도 함께 저장됨)
        orderRepository.save(order);
        return order.getId();
    }

    public Order findOne(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));
    }

    @Transactional(readOnly = true)
    public Page<OrderDto.Response> getOrderList(Pageable pageable){
        Page<Order> orderPage = orderRepository.findAllWithDetails(pageable);

        return orderPage.map(order -> OrderDto.Response.from(order));
    }
}
