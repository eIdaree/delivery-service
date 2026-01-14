package kz.eldar.microservice.delivery_service.services;

import kz.eldar.microservice.delivery_service.business.Delivery;
import kz.eldar.microservice.delivery_service.data.DeliveryRepository;
import kz.eldar.microservice.delivery_service.mapper.DeliveryMapper;
import kz.eldar.microservice.delivery_service.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.microservice.delivery_service.models.payload.delivery.DeliveryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public DeliveryResponseDto createDelivery(CreateDeliveryRequestDto request) {
        Delivery delivery = deliveryMapper.toEntity(request);
        delivery.setStatus("CREATED");
        Delivery saved = deliveryRepository.save(delivery);
        return deliveryMapper.toResponse(saved);
    }
}
