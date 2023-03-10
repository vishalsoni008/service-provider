package com.serviceprovider.request;

import lombok.Data;

@Data
public class ElectricianRequest {
    private String serviceName;
    private Double price;
    private String serviceManName;
}
