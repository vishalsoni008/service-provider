package com.serviceprovider.service.impl;

import com.serviceprovider.model.Plumber;
import com.serviceprovider.pagination.PlumberPagination;
import com.serviceprovider.repository.PlumberRepository;
import com.serviceprovider.request.PlumberRequest;
import com.serviceprovider.response.PlumberResponse;
import com.serviceprovider.service.PlumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
public class PlumberServiceImpl implements PlumberService {
    private PlumberRepository plumberRepository;

    @Autowired
    private PlumberServiceImpl(PlumberRepository plumberRepository){
        this.plumberRepository = plumberRepository;
    }

    private PlumberResponse toResponse(Plumber plumber){
            return PlumberResponse.builder()
                    .serviceName(plumber.getServiceName())
                    .price(plumber.getPrice())
                    .serviceManName(plumber.getServiceManName())
                    .build();
    }

    @Override
    public Page<PlumberResponse> getAllDetails() {
//        return new PageImpl<>(plumberRepository.findAll(Sort.by("price"))
        return new PageImpl<>(plumberRepository.findAll(PlumberPagination.firstTwo)
                .stream()
                .map(this :: toResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public PlumberResponse getById(Long id) {
        Plumber plumber =   plumberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No plumber available"));
        return toResponse(plumber);
    }

    @Override
    public PlumberResponse addRequest(PlumberRequest plumberRequest) {
        if(plumberRequest.getServiceName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pls provide service name");

        if(plumberRequest.getPrice() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pls chose a service price");

        if(plumberRequest.getServiceManName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pls select a person");

        Plumber plumber = new Plumber();

        plumber.setPrice(plumberRequest.getPrice());
        plumber.setServiceName(plumberRequest.getServiceName());
        plumber.setServiceManName(plumberRequest.getServiceManName());

        plumber = plumberRepository.save(plumber);

        return toResponse(plumber);
    }

    @Override
    public PlumberResponse updateService(Long id, PlumberRequest plumberRequest) {

        Plumber plumber = plumberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "no service found"));

        if(plumberRequest.getServiceName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pls provide service name");

        if(plumberRequest.getPrice() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pls chose a service price");

        if(plumberRequest.getServiceManName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pls select a person");


        plumber.setServiceName(plumberRequest.getServiceName());

        plumber = plumberRepository.save(plumber);

        return toResponse(plumber);
    }
}
