package pl.palkaszymon.ecommercespringboot.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.palkaszymon.ecommercespringboot.database.entity.OrderEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
