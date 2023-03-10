package com.serviceprovider.repository;

import com.serviceprovider.model.Plumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlumberRepository extends JpaRepository<Plumber, Long> {
}
