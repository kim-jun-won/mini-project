package hello.mini_project.dto;

import hello.mini_project.domain.Product;
import lombok.Builder;

public class ProductDto {

    public record Request(String name, Integer price, int stock){}

    @Builder
    public record Response(Long id, String name, Integer price, int stock){

        public static Response from(Product product) {
            return Response.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .build();
        }
    }

}
