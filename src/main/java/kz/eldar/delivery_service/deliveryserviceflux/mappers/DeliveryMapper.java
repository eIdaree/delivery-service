package kz.eldar.delivery_service.deliveryserviceflux.mappers;

import kz.eldar.delivery_service.deliveryserviceflux.entities.Delivery;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.CreateDeliveryRequestDto;
import kz.eldar.delivery_service.deliveryserviceflux.models.payload.delivery.DeliveryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    Delivery toEntity(CreateDeliveryRequestDto request);

    DeliveryResponseDto toResponse(Delivery delivery);
}
