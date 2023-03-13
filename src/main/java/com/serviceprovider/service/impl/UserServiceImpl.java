package com.serviceprovider.service.impl;

import com.serviceprovider.model.User;
import com.serviceprovider.repository.UserRepository;
import com.serviceprovider.request.UserRequest;
import com.serviceprovider.response.UserResponse;
import com.serviceprovider.service.UserService;
import com.serviceprovider.util.TimeSlug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .userFirstName(user.getUserFirstName())
                .userLastName(user.getUserLastName())
                .mailId(user.getMailId())
                .build();
    }

    @Override
    public Page<UserResponse> getAllUser() {
        return new PageImpl<>(userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public UserResponse addRequest(UserRequest userRequest) {
        if (userRequest.getUserFirstName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User first name is required");
        }
        if (userRequest.getUserLastName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Last name is required");
        }
        if (userRequest.getMailId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User MailId is Required");
        }
        if (userRequest.getUserDOB() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User DOB is Required");
        }
        if (userRequest.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Password is Required");
        }
        User user = new User();
        user.setUserFirstName(userRequest.getUserFirstName());
        user.setUserLastName(userRequest.getUserLastName());
        user.setUserDOB(userRequest.getUserDOB());
        user.setMailId(userRequest.getMailId());
        user.setPassword(userRequest.getPassword());
        user.setCreationDate(TimeSlug.simpleDateFormat.format(new Date()));

        user = userRepository.save(user);
        return toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User is not Found"));
        if (userRequest.getUserFirstName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User first name is required");
        }
        if (userRequest.getUserLastName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Last name is Required");
        }
        if (userRequest.getMailId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User mailId is required");
        }
        user.setPassword(userRequest.getPassword());
        user.setUpdationDate(TimeSlug.simpleDateFormat.format(new Date()));
        user = userRepository.save(user);
        return toResponse(user);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User is not Found"));
        return toResponse(user);
    }
}
