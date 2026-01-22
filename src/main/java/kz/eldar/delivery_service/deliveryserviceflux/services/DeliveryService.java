package kz.eldar.delivery_service.deliveryserviceflux.services;

import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import reactor.core.publisher.Mono;

public interface DeliveryService {
    Mono<DeliveryResponseDto> create(CreateDeliveryRequestDto createDeliveryRequestDto);
}
