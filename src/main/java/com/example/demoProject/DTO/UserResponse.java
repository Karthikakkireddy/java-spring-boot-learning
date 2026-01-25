package com.example.demoProject.DTO;


import com.example.demoProject.Model.UserRole;
import lombok.Data;

@Data
public class UserResponse
{
    private String id;
    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private UserRole userRole;

    private AddressDTO addressDTO;

}
