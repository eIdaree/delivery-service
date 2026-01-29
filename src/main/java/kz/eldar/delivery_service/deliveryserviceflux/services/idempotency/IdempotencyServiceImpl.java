package kz.eldar.delivery_service.deliveryserviceflux.services.idempotency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdempotencyServiceImpl {

    private final ReactiveStringRedisTemplate redisTemplate;
    private final IdempotencyKeyGenerator keyGenerator;
    private final ObjectMapper objectMapper;

    @Value("${idempotency.ttl-seconds:2}")
    private long ttlSeconds;

    public <T, R> Mono<R> executeWithIdempotency(
            String prefix,
            T request,
            Mono<R> operation,
            Class<R> resultClass
    ) {
        String key = keyGenerator.generateKey(prefix, request);

        return getFromCache(key, resultClass)
                .doOnNext(cached -> log.warn("Duplicate request detected, returning cached result"))
                .switchIfEmpty(
                        operation.flatMap(result ->
                                saveToCache(key, result)
                                        .thenReturn(result)
                        )
                );
    }

    private <R> Mono<R> getFromCache(String key, Class<R> resultClass) {
        return redisTemplate.opsForValue()
                .get(key)
                .flatMap(json -> deserialize(json, resultClass))
                .doOnNext(result -> log.info("Cache hit for key: {}", key));
    }

    private <R> Mono<Boolean> saveToCache(String key, R result) {
        return serialize(result)
                .flatMap(json ->
                        redisTemplate.opsForValue()
                                .set(key, json, Duration.ofSeconds(ttlSeconds))
                )
                .doOnSuccess(saved -> log.info("Cached result for key: {} (TTL: {}s)", key, ttlSeconds));
    }

    private <R> Mono<String> serialize(R object) {
        try {
            return Mono.just(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.error("Serialization error", e);
            return Mono.error(new RuntimeException("Failed to serialize result", e));
        }
    }

    private <R> Mono<R> deserialize(String json, Class<R> clazz) {
        try {
            return Mono.just(objectMapper.readValue(json, clazz));
        } catch (JsonProcessingException e) {
            log.error("Deserialization error", e);
            return Mono.empty();
        }
    }

    public Mono<Long> clearCache(String prefix) {
        String pattern = "idempotency:" + prefix + ":*";
        return redisTemplate.keys(pattern)
                .flatMap(redisTemplate::delete)
                .reduce(0L, Long::sum)
                .doOnSuccess(count -> log.info("Cleared {} keys with prefix: {}", count, prefix));
    }
}