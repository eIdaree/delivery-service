package kz.eldar.microservice.delivery_service.models.payload.delivery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDeliveryRequestDto(
        @NotNull
        Long productId,

        @NotBlank
        String address
) {
}
