package kz.eldar.delivery_service.deliveryserviceflux.models.payload.error;

import java.time.Instant;

public record ApiError(
        String code,
        String message,
        String service,
        Instant timestamp
) {}