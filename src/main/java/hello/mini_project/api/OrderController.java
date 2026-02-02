package hello.mini_project.api;

import hello.mini_project.domain.Order;
import hello.mini_project.dto.OrderDto;
import hello.mini_project.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 주문생성 ( 상품 ID와 수량을 받아 주문 처리 )
     * POST /api/orders
     * */
    @PostMapping
     public ResponseEntity<OrderDto.Response> createOrder(@RequestBody @Valid OrderDto.Request request)
     {
         // 주문 생성
         Long orderId = orderService.createorder(request.productId(), request.count());

         // 생성된 ID로 주문 엔티티를 다시 조회
         Order order = orderService.findOne(orderId);

         // 엔티티를 Response Dto로 변환하여 반환
         return ResponseEntity.ok(OrderDto.Response.from(order));
     }

     /**
      * 단건 주문 조회(테스트용)
      * GET /api/orders/{id}
      * */
     @GetMapping("/{id}")
    public ResponseEntity<OrderDto.Response> getOrder(@PathVariable Long id){
         Order order = orderService.findOne(id);
         return ResponseEntity.ok(OrderDto.Response.from(order));
     }


     /**
      * 주문목록 조회(Pagination)
      * GET /api/orders?page=0?size=10
      * */
     @GetMapping
    public ResponseEntity<Page<OrderDto.Response>> getOrders(
             @PageableDefault Pageable pageable){
         Page<OrderDto.Response> responses = orderService.getOrderList(pageable);

         return ResponseEntity.ok(responses);
     }


}
