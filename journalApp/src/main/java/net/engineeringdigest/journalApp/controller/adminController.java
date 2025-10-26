package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("/admin")
public class adminController
{   @Autowired
    UserService userService;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers()
    {
        List<User> users=userService.getAll();
        if (users!=null)
        {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
