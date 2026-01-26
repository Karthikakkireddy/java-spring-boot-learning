package com.example.demoProject.DTO;


import com.example.demoProject.Model.Address;
import com.example.demoProject.Model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest
{


    @NotBlank
    @Size(min = 2, max= 50)
    private String firstName;
    @Size(min = 2, max= 50)
    private String lastName;

    private String email;
    private String phone;

    private Address address;

    private UserRole role = UserRole.CUSTOMER;

}
