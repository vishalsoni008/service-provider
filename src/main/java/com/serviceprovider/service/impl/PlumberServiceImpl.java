package com.serviceprovider.service.impl;

import com.serviceprovider.model.Plumber;
import com.serviceprovider.pagination.PlumberPagination;
import com.serviceprovider.repository.PlumberRepository;
import com.serviceprovider.response.PlumberResponse;
import com.serviceprovider.service.PlumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
