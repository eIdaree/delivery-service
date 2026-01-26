package kz.eldar.delivery_service.deliveryserviceflux.services;

import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryStatusResponseDto;
import reactor.core.publisher.Mono;

public interface DeliveryService {
    Mono<DeliveryResponseDto> create(CreateDeliveryRequestDto createDeliveryRequestDto);
    Mono<DeliveryStatusResponseDto> getStatus(Long deliveryId);
}
