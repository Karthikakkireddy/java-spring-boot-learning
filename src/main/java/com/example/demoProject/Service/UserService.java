package com.example.demoProject.Service;

import com.example.demoProject.DTO.AddressDTO;
import com.example.demoProject.DTO.UserRequest;
import com.example.demoProject.DTO.UserResponse;
import com.example.demoProject.Exceptions.UserNotFoundException;
import com.example.demoProject.Model.Address;
import com.example.demoProject.Model.Users;
import com.example.demoProject.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService
{
    @Autowired
    private UserRepo userRepo;


    public List<UserResponse> getUsersService()
    {
        return userRepo.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }


    public UserResponse registerUserService( UserRequest newUserRequest)
    {
        Users newUser = mapToUser(newUserRequest);
        Users registeredUser = userRepo.save(newUser);

        return mapToUserResponse(registeredUser);
    }


    public UserResponse getUserByIdService(Long id)
    {
        return userRepo.findById(id).map(this::mapToUserResponse)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    public UserResponse updateUserService( UserRequest updatedUserDataRequest,  Long id)
    {

        Users user  = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

            user.setFirstName(updatedUserDataRequest.getFirstName());
            user.setLastName(updatedUserDataRequest.getLastName());
            user.setPhone(updatedUserDataRequest.getPhone());
            user.setEmail(updatedUserDataRequest.getEmail());
            user.setRole(updatedUserDataRequest.getRole());
            user.setAddress(updatedUserDataRequest.getAddress());

            return mapToUserResponse(userRepo.save(user));
    }

    public UserResponse deleteUserService( Long id)
    {
          Users user =   userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

          userRepo.delete(user);

          return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(Users user)
    {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setUserRole(user.getRole());

        if(user.getAddress() != null)
        {
            AddressDTO addressDTO = new AddressDTO();

            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddressDTO(addressDTO);

        }

        return userResponse;
    }

    private Users mapToUser(UserRequest userRequest)
    {
        Users newUser = new Users();

        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPhone(userRequest.getPhone());
        newUser.setRole(userRequest.getRole());

        if(userRequest.getAddress() != null)
        {
            Address address = new Address();

            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            newUser.setAddress(address);
        }
        return newUser;

    }
}
