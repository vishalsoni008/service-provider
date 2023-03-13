package com.serviceprovider.service;

import com.serviceprovider.request.PriceRequest;
import com.serviceprovider.response.PriceResponse;
import org.springframework.data.domain.Page;

public interface PriceService {

    Page<PriceResponse> getAllPriceDetails();

    PriceResponse addNewRequest(PriceRequest priceRequest);

    PriceResponse updatePrice(Long id, PriceRequest priceRequest);

    PriceResponse getPriceById(Long id);

    void removePriceById(Long id);
}
