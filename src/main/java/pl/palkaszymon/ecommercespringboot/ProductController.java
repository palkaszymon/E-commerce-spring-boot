package pl.palkaszymon.ecommercespringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.palkaszymon.ecommercespringboot.database.ProductEntity;
import pl.palkaszymon.ecommercespringboot.domain.request.NewProductRequest;
import pl.palkaszymon.ecommercespringboot.domain.request.UpdateProductRequest;
import pl.palkaszymon.ecommercespringboot.domain.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductEntity getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public ProductEntity saveProduct(@RequestBody NewProductRequest request) {
        return productService.createProduct(request);
    }

    @PatchMapping("/{productId}")
    public ProductEntity updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(productId, request);
    }
}
