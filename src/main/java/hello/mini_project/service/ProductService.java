package hello.mini_project.service;

import hello.mini_project.domain.Product;
import hello.mini_project.dto.ProductDto;
import hello.mini_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long saveProduct(ProductDto.Request request){
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .build();

        return productRepository.save(product).getId();
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Product findOne(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
    }

    @Transactional
    public void updateProduct(Long id, ProductDto.Request request) {
        Product product = productRepository.findById(id).orElseThrow();

        if (request.name() != null) product.setName(request.name());
        if (request.price() != null) product.setPrice(request.price());
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
