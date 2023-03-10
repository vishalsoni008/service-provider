package com.serviceprovider.service;

import com.serviceprovider.request.ElectricianRequest;
import com.serviceprovider.response.ElectricianResponse;
import org.springframework.data.domain.Page;

public interface ElectricianService {
    Page<ElectricianResponse> getAllDetails();
    ElectricianResponse addRequest(ElectricianRequest electricianRequest);
    ElectricianResponse updateService(Long id, ElectricianRequest electricianRequest);
    ElectricianResponse getById(Long id);


}
