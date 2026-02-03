1. N+1 문제 해결

* 수정전
```
Page<Order> orderPage = orderRepository.findAllWithDetails(pageable);
```

* 수정후
```
// OrderRepository
@Query(value = "select distinct o from Order o join fetch o.orderItems oi join fetch oi.product",
      countQuery = "select count(o) from Order o")
Page<Order> findAllWithDetails(Pageable pageable);

// OrderService
Page<Order> orderPage = orderRepository.findAll(pageable);
```

* 쿼리문 로그
```
    select
        distinct o1_0.id,
        o1_0.order_date,
        oi1_0.order_id,
        oi1_0.id,
        oi1_0.count,
        oi1_0.order_price,
        p1_0.product_id,
        p1_0.name,
        p1_0.price,
        p1_0.stock 
    from
        orders o1_0 
    join
        order_item oi1_0 
            on o1_0.id=oi1_0.order_id 
    join
        product p1_0 
            on p1_0.product_id=oi1_0.product_id
```

fetch join을 이용해 orderItem, product의 정보까지 가져오는 것을 확인 할 수 있다.



2. 재고감소 기능 시나리오 - Postman 테스트


1. 두바이쫀득쿠키 재고 2개 
<img width="456" height="240" alt="image" src="https://github.com/user-attachments/assets/e6b8b970-fcfb-46b6-b9ff-c1befa3d9630" />

2. 두바이쫀득쿠키 주문(주문수량:1) -> 두바이쫀득쿠키 주문(주문수량:2) 
<img width="1052" height="374" alt="image" src="https://github.com/user-attachments/assets/4c59c730-07f1-4b42-a506-1fa5d766214c" />




