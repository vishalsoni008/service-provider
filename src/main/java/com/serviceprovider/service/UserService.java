package com.serviceprovider.service;

import com.serviceprovider.request.UserRequest;
import com.serviceprovider.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {


    Page<UserResponse> getAllUser();

    UserResponse addRequest(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    UserResponse getById(Long id);
}
