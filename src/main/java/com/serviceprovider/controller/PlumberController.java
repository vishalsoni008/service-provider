package com.serviceprovider.controller;

import com.serviceprovider.request.PlumberRequest;
import com.serviceprovider.response.PlumberResponse;
import com.serviceprovider.service.PlumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plumber")
public class PlumberController {

    private final PlumberService plumberService;

    @Autowired
    public PlumberController(PlumberService plumberService) {
        this.plumberService = plumberService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<PlumberResponse>> getAllDetails() {
        return ResponseEntity.ok(plumberService.getAllDetails());
    }

    @PostMapping("/")
    public ResponseEntity<PlumberResponse> addNewRequest(@RequestBody PlumberRequest plumberRequest) {
        return new ResponseEntity<>(plumberService.addRequest(plumberRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlumberResponse> updateService(@PathVariable Long id, @RequestBody PlumberRequest plumberRequest) {
        return new ResponseEntity<>(plumberService.updateService(id, plumberRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlumberResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(plumberService.getById(id));
    }

}
