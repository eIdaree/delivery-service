package kz.eldar.delivery_service.deliveryserviceflux.services;

import kz.eldar.delivery_service.deliveryserviceflux.exceptions.NotFoundException;
import kz.eldar.delivery_service.deliveryserviceflux.mappers.DeliveryMapper;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryStatusResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.repos.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public Mono<DeliveryResponseDto> create(CreateDeliveryRequestDto requestDto){
        return Mono.just(requestDto)
                .map(deliveryMapper::toEntity)
                .flatMap(deliveryRepository::save)
                .map(deliveryMapper::toResponse)
                .doOnSuccess(delivery -> log.info("Delivery created: {}", delivery.id()))
                .doOnError(ex -> log.error("Failed to create delivery", ex));
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
