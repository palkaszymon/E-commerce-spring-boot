package pl.palkaszymon.ecommercespringboot.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import pl.palkaszymon.ecommercespringboot.database.ProductEntity;
import pl.palkaszymon.ecommercespringboot.database.ProductRepository;
import pl.palkaszymon.ecommercespringboot.domain.exception.ProductNotFoundException;
import pl.palkaszymon.ecommercespringboot.domain.model.Category;
import pl.palkaszymon.ecommercespringboot.domain.request.NewProductRequest;
import pl.palkaszymon.ecommercespringboot.domain.request.UpdateProductRequest;
import pl.palkaszymon.ecommercespringboot.domain.validator.RequestValidator;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RequestValidator requestValidator;

    public ProductEntity createProduct(NewProductRequest request) {
        requestValidator.validateNewProductRequest(request);
        ProductEntity productToSave = new ProductEntity(null, request.name(), request.description(), Category.fromString(request.category()), request.price());
        return productRepository.save(productToSave);
    }

    public ProductEntity updateProduct(Long productId, UpdateProductRequest request) {
        requestValidator.validateUpdateProductRequest(request);
        ProductEntity productToUpdate = this.getProductById(productId);
        return productRepository.save(this.prepareEntityToUpdate(productToUpdate, request));
    }

    public ProductEntity getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with ID: %s not found", productId)));
    }

    public List<ProductEntity> getAllProducts() {
        return Streamable.of(productRepository.findAll()).toList();
    }

    private ProductEntity prepareEntityToUpdate(ProductEntity foundEntity, UpdateProductRequest updateRequest) {
        if (updateRequest.name() != null) {
            foundEntity.setName(updateRequest.name());
        }
        if (updateRequest.description() != null) {
            foundEntity.setDescription(updateRequest.description());
        }
        if (updateRequest.category() != null) {
            foundEntity.setCategory(Category.fromString(updateRequest.category()));
        }
        if (updateRequest.price() != null) {
            foundEntity.setPrice(updateRequest.price());
        }
        return foundEntity;
    }
}
