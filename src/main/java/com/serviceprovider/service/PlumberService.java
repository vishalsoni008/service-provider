package com.serviceprovider.service;

import com.serviceprovider.request.PlumberRequest;
import com.serviceprovider.response.PlumberResponse;
import org.springframework.data.domain.Page;

public interface PlumberService {
    Page<PlumberResponse> getAllDetails();

    PlumberResponse addRequest(PlumberRequest plumberRequest);

    PlumberResponse updateService(Long id, PlumberRequest plumberRequest);

    PlumberResponse getById(Long id);
}
