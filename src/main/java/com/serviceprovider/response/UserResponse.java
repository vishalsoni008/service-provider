package com.serviceprovider.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String userFirstName;
    private String userLastName;
    private String mailId;
}
