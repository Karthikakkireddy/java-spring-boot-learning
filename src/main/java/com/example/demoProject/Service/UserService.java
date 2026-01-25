package com.example.demoProject.Service;

import com.example.demoProject.Exceptions.UserNotFoundException;
import com.example.demoProject.Model.Users;
import com.example.demoProject.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepo userRepo;


    public List<Users> getUsersService()
    {
        return userRepo.findAll();
    }


    public Users registerUserService( Users newUser)
    {

        return userRepo.save(newUser);
    }


    public Users getUserByIdService(Long id)
    {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    public Users updateUserService( Users updatedUserData,  Long id)
    {
        Users user  = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

            user.setFirstName(updatedUserData.getFirstName());
            user.setLastName(updatedUserData.getLastName());
            userRepo.save(user);
            return userRepo.save(user);
    }

    public Users deleteUserService( Long id)
    {
            return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
