package com.example.demoProject.Service;

import com.example.demoProject.Model.Users;
import com.example.demoProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepo userRepo;


    public ResponseEntity<List<Users>> getUsersService()
    {
        List<Users>  usersList = userRepo.findAll();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }


    public ResponseEntity<Users> registerUserService( Users newUser)
    {

        userRepo.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<Users> getUserByIdService( Long id)
    {
        Users user  = userRepo.findById(id).orElse(null);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Users> updateUserService( Users updatedUserData,  Long id)
    {
        Users user  = userRepo.findById(id).orElse(null);
        if(user != null)
        {
            user.setFirstName(updatedUserData.getFirstName());
            user.setLastName(updatedUserData.getLastName());
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Users> deleteUserService( Long id)
    {
        Users user  = userRepo.findById(id).orElse(null);
        if(user != null)
        {
            userRepo.deleteById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
}
