package com.kelsonthony.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class OrderDTO {
    private Long orderId;
    private Long customerId;
    private String customerName;
    private Long productId;
    private BigDecimal orderValue;
}
