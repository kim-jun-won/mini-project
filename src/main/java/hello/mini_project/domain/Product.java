package hello.mini_project.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor // JPA 기본 생성자 필수
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private Integer price;
    private int stock;

    @Builder // 빌더 추가
    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // 상품 수정을 위한 비즈니스 로직
    public void update(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public void removeStock(int quantity){
        int reststock = this.stock - quantity;
        if(reststock < 0)
            throw new RuntimeException("재고가 부족합니다. (현재 재고 : " + this.stock + ")");
        stock -= quantity;
    }

}