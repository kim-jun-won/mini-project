package hello.mini_project.domain;

import jakarta.persistence.*;
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

    // 상품 수정을 위한 비즈니스 로직
    public void update(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}