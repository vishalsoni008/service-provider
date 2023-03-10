package com.serviceprovider.request;

import lombok.Data;

@Data
public class PlumberRequest {
    private String serviceName;

    private Double price;

    private String serviceManName;
}
