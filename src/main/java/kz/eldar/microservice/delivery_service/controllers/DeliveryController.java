package kz.eldar.microservice.delivery_service.controllers;

import jakarta.validation.Valid;
import kz.eldar.microservice.delivery_service.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.microservice.delivery_service.models.payload.delivery.DeliveryResponseDto;
import kz.eldar.microservice.delivery_service.services.DeliveryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryServiceImpl deliveryService;

    @PostMapping
    public DeliveryResponseDto create(@Valid @RequestBody CreateDeliveryRequestDto request) {
        return deliveryService.createDelivery(request);
    }
}
