package com.serviceprovider.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceResponse {
    private Double price;
}
