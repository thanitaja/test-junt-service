package com.example.test.sql.Controllers;

import com.example.test.sql.Entities.User;
import com.example.test.sql.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public User get(
         @RequestParam Integer id
    ) {
        return userService.get(id);
    }

}
