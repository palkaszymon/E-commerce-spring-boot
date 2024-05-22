package pl.palkaszymon.ecommercespringboot.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.palkaszymon.ecommercespringboot.database.entity.ProductEntity;
import pl.palkaszymon.ecommercespringboot.domain.request.NewProductRequest;
import pl.palkaszymon.ecommercespringboot.domain.request.UpdateProductRequest;
import pl.palkaszymon.ecommercespringboot.domain.service.ProductService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        log.info("Get all products request");
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductEntity getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public ProductEntity saveProduct(@RequestBody NewProductRequest request) {
        log.info("Saving product: {}", request.name());
        return productService.createProduct(request);
    }

    @PatchMapping("/{productId}")
    public ProductEntity updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping
    public void deleteProduct() {
        productService.deleteAllProducts();
        log.info("Deleted all products");
    }
}
