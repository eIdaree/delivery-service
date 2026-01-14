package kz.eldar.microservice.delivery_service.data;

import kz.eldar.microservice.delivery_service.business.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {}
