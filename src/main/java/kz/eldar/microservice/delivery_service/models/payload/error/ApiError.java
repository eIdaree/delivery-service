package kz.eldar.microservice.delivery_service.models.payload.error;

import java.time.Instant;

public record ApiError(
        String code,
        String message,
        String service,
        Instant timestamp
) {}