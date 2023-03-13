package com.serviceprovider.controller;

import com.serviceprovider.request.ElectricianRequest;
import com.serviceprovider.response.ElectricianResponse;
import com.serviceprovider.service.ElectricianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/electrician")
public class ElectricianController {

    private final ElectricianService electricianService;

    @Autowired
    public ElectricianController(ElectricianService electricianService) {
        this.electricianService = electricianService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<ElectricianResponse>> getAllDetails() {
        return ResponseEntity.ok(electricianService.getAllDetails());
    }

    @PostMapping("/")
    public ResponseEntity<ElectricianResponse> addNewRequest(@RequestBody ElectricianRequest electricianRequest) {
        return new ResponseEntity<>(electricianService.addRequest(electricianRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ElectricianResponse> updateService(@PathVariable Long id, @RequestBody ElectricianRequest electricianRequest) {
        return new ResponseEntity<>(electricianService.updateService(id, electricianRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectricianResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(electricianService.getById(id));
    }
}
