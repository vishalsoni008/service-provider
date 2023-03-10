package com.serviceprovider.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ElectricianPagination {
    Pageable firstTwo = PageRequest.of(0,15);
}
