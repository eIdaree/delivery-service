package kz.eldar.delivery_service.deliveryserviceflux.repos;

import kz.eldar.delivery_service.deliveryserviceflux.entities.Delivery;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DeliveryRepository extends ReactiveCrudRepository<Delivery, Long> {
}
