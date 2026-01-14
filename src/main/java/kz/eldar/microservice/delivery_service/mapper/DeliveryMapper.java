package kz.eldar.microservice.delivery_service.mapper;

import kz.eldar.microservice.delivery_service.business.Delivery;
import kz.eldar.microservice.delivery_service.models.DeliveryDto;
import kz.eldar.microservice.delivery_service.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.microservice.delivery_service.models.payload.delivery.DeliveryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    Delivery toEntity(CreateDeliveryRequestDto request);

//    CreateDeliveryRequestDto toDto(@MappingTarget DeliveryDto delivery);

    DeliveryResponseDto toResponse(Delivery delivery);
}
