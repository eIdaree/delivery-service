package kz.eldar.delivery_service.deliveryserviceflux.services;

import kz.eldar.delivery_service.deliveryserviceflux.entities.Delivery;
import kz.eldar.delivery_service.deliveryserviceflux.exceptions.NotFoundException;
import kz.eldar.delivery_service.deliveryserviceflux.mappers.DeliveryMapper;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryStatusResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.repos.DeliveryRepository;
import kz.eldar.delivery_service.deliveryserviceflux.services.idempotency.IdempotencyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final IdempotencyServiceImpl idempotencyService;


    @Override
    public Mono<DeliveryResponseDto> create(CreateDeliveryRequestDto request) {
        log.info("Processing delivery creation for product: {}", request.productId());

        Mono<DeliveryResponseDto> createOperation = doCreate(request);

        return idempotencyService.executeWithIdempotency(
                "delivery",
                request,
                createOperation,
                DeliveryResponseDto.class
        );
    }
    private Mono<DeliveryResponseDto> doCreate(CreateDeliveryRequestDto request) {
        Delivery delivery = Delivery.builder()
                .productId(request.productId())
                .address(request.address())
                .status("CREATED")
                .build();

        return deliveryRepository.save(delivery)
                .doOnSuccess(saved -> log.info("Delivery created: {}", saved.getId()))
                .map(deliveryMapper::toResponse);
    }

    @Override
    public Mono<DeliveryStatusResponseDto> getStatus(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .switchIfEmpty(Mono.error(
                        new NotFoundException("Delivery with id " + deliveryId + " not found")
                ))
                .map(deliveryMapper::toStatusResponse);
    }
}
