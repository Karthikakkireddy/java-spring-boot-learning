package com.example.demoProject.DTO;


import com.example.demoProject.Model.Address;
import com.example.demoProject.Model.UserRole;
import lombok.Data;

@Data
public class UserRequest
{

    private String firstName;
    private String lastName;

    private String email;
    private String phone;

    private Address address;

    private UserRole role = UserRole.CUSTOMER;

}
