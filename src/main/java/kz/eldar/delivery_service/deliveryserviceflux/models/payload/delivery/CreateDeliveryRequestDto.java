package kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDeliveryRequestDto(
        @NotNull
        Long productId,

        @NotBlank
        String address
) {
}
