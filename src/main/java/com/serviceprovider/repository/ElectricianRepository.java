package com.serviceprovider.repository;

import com.serviceprovider.model.Electrician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricianRepository extends JpaRepository<Electrician, Long> {
}
