package com.example.demoProject.Controller;

import com.example.demoProject.Model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController
{
    private List<Users> usersList = new ArrayList<>();

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers()
    {
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Users> registerUser(@RequestBody Users newUser)
    {
        usersList.add(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id)
    {
        for(Users user : usersList)
        {
            if(user.getId() == id) {
                return new ResponseEntity<>(user, HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
