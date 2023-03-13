package com.serviceprovider.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceprovider.model.Price;
import com.serviceprovider.repository.PriceRepository;
import com.serviceprovider.request.PriceRequest;
import com.serviceprovider.response.PriceResponse;
import com.serviceprovider.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }
//    private PriceResponse toResponse(Price price){
//        return PriceResponse.builder()
//                .price(price.getPrice())
//                .build();
//    }
    @Override
    public Page<PriceResponse> getAllPriceDetails() {
        return new PageImpl<>(priceRepository.findAll()
                .stream()
                .map(response -> objectMapper.writeValue(response, PriceResponse.class))
                .collect(Collectors.toList()));
    }

    @Override
    public PriceResponse addNewRequest(PriceRequest priceRequest) {
        if (priceRequest.getPrice() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price is required");
        }
        Price price = new Price();
        price.setPrice(priceRequest.getPrice());
        price = priceRepository.save(price);
        return objectMapper.convertValue(price, PriceResponse.class);
    }

    @Override
    public PriceResponse updatePrice(Long id, PriceRequest priceRequest) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Plan is not Found"));
        if (priceRequest.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price is required");
        }
        price.setPrice(priceRequest.getPrice());
        price = priceRepository.save(price);
        return objectMapper.convertValue(price, PriceResponse.class);
    }

    @Override
    public PriceResponse getPriceById(Long id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Plan is not available"));
        return objectMapper.convertValue(price, PriceResponse.class);
    }

    @Override
    public void removePriceById(Long id) {
        priceRepository.deleteById(id);
    }
}
