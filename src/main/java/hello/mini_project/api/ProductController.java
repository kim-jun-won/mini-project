package hello.mini_project.api;

import hello.mini_project.domain.Product;
import hello.mini_project.dto.ProductDto;
import hello.mini_project.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 등록
     * Post /api/products
     * */
    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductDto.Request request){
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());

        Long create_productid = productService.saveProduct(product);
        return ResponseEntity.ok(create_productid);
    }

    /**
     * 상품 목록조회
     * GET /api/products
     * */
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getProducts(){
        List<ProductDto.Response> responses = productService.findProducts().stream()
                .map(ProductDto.Response::from)
                .toList();

        return ResponseEntity.ok(responses);
    }


    /**
     * 상품 정보 수정 (이름, 가격 변경)
     * PATCH /api/products/{id}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductDto.Request request) {

        // 변경 감지(Dirty Checking)를 이용한 수정 수행
        productService.updateProduct(id, request);

        // 수정 성공 시 본문 없이 200 OK 응답
        return ResponseEntity.ok().build();
    }

    /**
     * 상품 삭제
     * DELETE /api/products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        // 삭제 성공 시 204 No Content 또는 200 OK 반환
        return ResponseEntity.noContent().build();
    }

}
