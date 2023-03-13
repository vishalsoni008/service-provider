package com.serviceprovider.controller;

import com.serviceprovider.request.PriceRequest;
import com.serviceprovider.response.PriceResponse;
import com.serviceprovider.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<PriceResponse>> getAllPrice() {
        return ResponseEntity.ok(priceService.getAllPriceDetails());
    }

    @PostMapping("/")
    public ResponseEntity<PriceResponse> addPrice(@RequestBody PriceRequest priceRequest) {
        return new ResponseEntity<>(priceService.addNewRequest(priceRequest), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PriceResponse> updatePrice(@PathVariable Long id, @RequestBody PriceRequest priceRequest) {
        return new ResponseEntity<>(priceService.updatePrice(id, priceRequest), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PriceResponse> getPriceById(@PathVariable Long id) {
        return ResponseEntity.ok(priceService.getPriceById(id));
    }
    @DeleteMapping("/{id}")
    public void removePriceDetail(@PathVariable Long id){
        priceService.removePriceById(id);
    }
}
