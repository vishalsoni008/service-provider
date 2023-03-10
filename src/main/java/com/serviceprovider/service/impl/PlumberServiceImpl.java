package com.serviceprovider.service.impl;

import com.serviceprovider.repository.PlumberRepository;
import com.serviceprovider.response.PlumberResponse;
import com.serviceprovider.service.PlumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class PlumberServiceImpl implements PlumberService {
    private PlumberRepository plumberRepository;

    @Autowired
    private PlumberServiceImpl(PlumberRepository plumberRepository){
        this.plumberRepository = plumberRepository;
    }

    @Override
    public Page<PlumberResponse> getAllDetails() {
        return new PageImpl<>();
    }
}
