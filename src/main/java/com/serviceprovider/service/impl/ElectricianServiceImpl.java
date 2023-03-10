package com.serviceprovider.service.impl;

import com.serviceprovider.model.Electrician;
import com.serviceprovider.pagination.ElectricianPagination;
import com.serviceprovider.repository.ElectricianRepository;
import com.serviceprovider.request.ElectricianRequest;
import com.serviceprovider.response.ElectricianResponse;
import com.serviceprovider.service.ElectricianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
public class ElectricianServiceImpl implements ElectricianService {

    private ElectricianRepository electricianRepository;
    @Autowired
    public ElectricianServiceImpl(ElectricianRepository electricianRepository){
        this.electricianRepository = electricianRepository;
    }
    private ElectricianResponse toResponse(Electrician electrician){
        return  ElectricianResponse.builder()
                .serviceName(electrician.getServiceName())
                .price(electrician.getPrice())
                .serviceManName(electrician.getServiceManName())
                .build();
    }

    @Override
    public Page<ElectricianResponse> getAllDetails() {
//        return new PageImpl<>(electricianRepository.findAll(Sort.by("price")));
        return new PageImpl<>(electricianRepository.findAll(ElectricianPagination.firstTwo)
                .stream()
                .map(this :: toResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public ElectricianResponse addRequest(ElectricianRequest electricianRequest) {
        if(electricianRequest.getServiceName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please Provide Service Name");

        if (electricianRequest.getPrice() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please Choose Service Price");

        if (electricianRequest.getServiceManName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please Select the Service Person");

        Electrician electrician = new Electrician();
        electrician.setServiceName(electricianRequest.getServiceName());
        electrician.setPrice(electricianRequest.getPrice());
        electrician.setServiceManName(electricianRequest.getServiceManName());

        electrician = electricianRepository.save(electrician);

        return toResponse(electrician);
    }

    @Override
    public ElectricianResponse updateService(Long id, ElectricianRequest electricianRequest) {
        Electrician electrician = electricianRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT,"No electrician available"));

        if(electricianRequest.getServiceName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please Provide Service Name");

        if (electricianRequest.getPrice() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please Choose Service Price");

        if (electricianRequest.getServiceManName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please Select the Service Person");

        electrician.setServiceName(electricianRequest.getServiceName());
        electrician = electricianRepository.save(electrician);

        return toResponse(electrician);
    }

    @Override
    public ElectricianResponse getById(Long id) {
        Electrician electrician = electricianRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT,"No electrician available"));
        return toResponse(electrician);
    }
}
