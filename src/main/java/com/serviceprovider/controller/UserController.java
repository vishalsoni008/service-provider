package com.serviceprovider.controller;

import com.serviceprovider.request.UserRequest;
import com.serviceprovider.response.UserResponse;
import com.serviceprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Page<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUserRequest(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.addRequest(userRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/updateUser/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }






}
