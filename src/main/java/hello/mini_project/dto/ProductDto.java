package hello.mini_project.dto;

import hello.mini_project.domain.Product;
import lombok.Builder;

public class ProductDto {

    public record Request(String name, Integer price){}

    @Builder
    public record Response(Long id, String name, Integer price){

        public static Response from(Product product) {
            return Response.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        }
    }

}
