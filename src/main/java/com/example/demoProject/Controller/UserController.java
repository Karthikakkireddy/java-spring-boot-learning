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
        List<Users > usersList =  userService.getUsersService();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Users> registerUser(@RequestBody Users newUser)
    {
        Users registeredUser = userService.registerUserService(newUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id)
    {
       Users fetchedUseer = userService.getUserByIdService(id);
        return new ResponseEntity<>(fetchedUseer, HttpStatus.FOUND);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users updatedUserData, @PathVariable Long id)
    {
        Users updatedUser = userService.updateUserService(updatedUserData, id);;

       return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Long id)
    {
        Users deletedUser =  userService.deleteUserService(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);

    }
}
