package com.example.demoProject.Controller;

import com.example.demoProject.DTO.UserRequest;
import com.example.demoProject.DTO.UserResponse;
import com.example.demoProject.Model.Users;
import com.example.demoProject.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController
{


    private final UserService userService;




    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponse>> getUsers()
    {
        List<UserResponse> usersList =  userService.getUsersService();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest newUserRequest)
    {
        UserResponse registeredUser = userService.registerUserService(newUserRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
    {
       UserResponse fetchedUser = userService.getUserByIdService(id);
        return new ResponseEntity<>(fetchedUser, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest updatedUserDataRequest, @PathVariable Long id)
    {
        UserResponse  updatedUserResponse = userService.updateUserService(updatedUserDataRequest, id);;

       return new ResponseEntity<>(updatedUserResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUserService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
