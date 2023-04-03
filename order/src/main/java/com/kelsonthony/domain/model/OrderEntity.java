package com.kelsonthony.domain.model;

import io.quarkus.arc.All;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderProducts")
@Data
@All
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long orderId;
    private Long customerId;
    private String customerName;
    private Long productId;
    private BigDecimal orderValue;
}
