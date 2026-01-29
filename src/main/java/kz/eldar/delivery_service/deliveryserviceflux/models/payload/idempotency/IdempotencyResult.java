package kz.eldar.delivery_service.deliveryserviceflux.models.payload.idempotency;

public record IdempotencyResult<T>(
        boolean isDuplicate,
        T data,
        String deliveryId
) {
    public static <T> IdempotencyResult<T> duplicate(T data, String deliveryId) {
        return new IdempotencyResult<>(true, data, deliveryId);
    }

    public static <T> IdempotencyResult<T> fresh(T data, String deliveryId) {
        return new IdempotencyResult<>(false, data, deliveryId);
    }
}