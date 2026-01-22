package kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery;

public record DeliveryResponseDto(
        Long id,
        Long productId,
        String address,
        String status
) {
}
