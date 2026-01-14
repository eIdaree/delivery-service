package kz.eldar.microservice.delivery_service.services;

import kz.eldar.microservice.delivery_service.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.microservice.delivery_service.models.payload.delivery.DeliveryResponseDto;

public interface DeliveryService {
    DeliveryResponseDto createDelivery(CreateDeliveryRequestDto request);
}
