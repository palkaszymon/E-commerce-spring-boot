package pl.palkaszymon.ecommercespringboot.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.palkaszymon.ecommercespringboot.database.entity.ProductEntity;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
