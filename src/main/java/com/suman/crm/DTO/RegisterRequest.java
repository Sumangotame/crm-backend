package com.suman.crm.DTO;
import lombok.Data;

@Data
public class RegisterRequest {
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private String role;
}
