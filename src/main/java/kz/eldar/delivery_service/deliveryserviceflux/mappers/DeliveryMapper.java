package kz.eldar.delivery_service.deliveryserviceflux.mappers;

import kz.eldar.delivery_service.deliveryserviceflux.entities.Delivery;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryStatusResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    Delivery toEntity(CreateDeliveryRequestDto request);

    DeliveryResponseDto toResponse(Delivery delivery);

    @Mapping(source = "id", target = "deliveryId")
    @Mapping(source = "status", target = "status")
    DeliveryStatusResponseDto toStatusResponse(Delivery delivery);
}
