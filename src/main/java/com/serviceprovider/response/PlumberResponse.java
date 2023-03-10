package com.serviceprovider.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlumberResponse {

    private String serviceName;

    private Double price;

    private String serviceManName;
}
