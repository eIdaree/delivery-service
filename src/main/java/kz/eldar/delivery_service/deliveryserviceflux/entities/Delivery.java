package kz.eldar.delivery_service.deliveryserviceflux.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("delivery")
public class Delivery {
    @Id
    private Long id;

    private Long productId;
    private String address;
    private String status;
}

