package com.example.demoProject.Controller;

import com.example.demoProject.Model.Users;
import com.example.demoProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;



    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers()
    {
        return userService.getUsersService();
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Users> registerUser(@RequestBody Users newUser)
    {
       return userService.registerUserService(newUser);
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id)
    {
        return userService.getUserByIdService(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users updatedUserData, @PathVariable Long id)
    {
       return userService.updateUserService(updatedUserData, id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Long id)
    {
        return userService.deleteUserService(id);

    }
}
