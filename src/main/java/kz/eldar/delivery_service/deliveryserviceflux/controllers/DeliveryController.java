package kz.eldar.delivery_service.deliveryserviceflux.controllers;

import jakarta.validation.Valid;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryStatusResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public Mono<DeliveryResponseDto> create(@Valid @RequestBody CreateDeliveryRequestDto request) {
        return deliveryService.create(request);
    }

    @GetMapping("status/{id}")
    public Mono<DeliveryStatusResponseDto> getStatus(@PathVariable Long id) {
        return deliveryService.getStatus(id);
    }
}
