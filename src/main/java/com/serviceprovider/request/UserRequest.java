package com.serviceprovider.request;

import lombok.Data;

@Data
public class UserRequest {
    private String userFirstName;
    private String userLastName;
    private String userDOB;
    private String mailId;
    private String password;
}
