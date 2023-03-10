package com.serviceprovider.service;

import com.serviceprovider.response.PlumberResponse;
import org.springframework.data.domain.Page;

public interface PlumberService {
    Page<PlumberResponse> getAllDetails();
}
