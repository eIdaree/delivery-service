package kz.eldar.delivery_service.deliveryserviceflux.services.idempotency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Component
@RequiredArgsConstructor
@Slf4j
public class IdempotencyKeyGenerator {

    private final ObjectMapper objectMapper;

    public String generateKey(String prefix, Object request) {
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            String hash = sha256(jsonRequest);
            log.info("Parameter {}", request.getClass().toString());
            String result = String.format("idempotency:%s:%s", prefix, hash);
            log.info(result);
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize request", e);
        }
    }

    private String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}