package hello.mini_project.repository;

import hello.mini_project.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select distinct o from Order o join fetch o.orderItems oi join fetch oi.product",
          countQuery = "select count(o) from Order o")
    Page<Order> findAllWithDetails(Pageable pageable);
}
