package com.serviceprovider.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userFirstName;
    private String userLastName;
    private String userDOB;
    private String mailId;
    private String password;
    private LocalDateTime creationDate;
    private LocalDateTime updationDate;

}
