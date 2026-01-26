package kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery;

public record DeliveryStatusResponseDto(
        Long deliveryId,
        String status
) {}
