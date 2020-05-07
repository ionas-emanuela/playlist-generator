package com.project.controller;

import com.project.entities.User;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationUtils {

    @Autowired
    private UserService userService;

    public boolean validateUsername(String username){

        User user = null;
        user = userService.findByUsername(username);

        if(null != user){
            return false;
        }

        String regex = "^[a-zA-Z]+[a-zA-Z0-9._]*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }
}
