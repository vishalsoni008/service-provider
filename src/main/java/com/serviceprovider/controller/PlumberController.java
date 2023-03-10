package com.serviceprovider.controller;

import com.serviceprovider.response.PlumberResponse;
import com.serviceprovider.service.PlumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plumber")
public class PlumberController {

    private PlumberService plumberService;

    @Autowired
    private PlumberController(PlumberService plumberService){
        this.plumberService = plumberService;
    }

    @GetMapping("/")
    public ResponseEntity<PlumberResponse> getAllDetails(){
        return ResponseEntity.ok(plumberService.getAllDetails());
    }


}
