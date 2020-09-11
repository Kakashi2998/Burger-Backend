package com.ApoorvMathur.BurgerBackend.Controller;

import com.ApoorvMathur.BurgerBackend.DAO.UserDAO;
import com.ApoorvMathur.BurgerBackend.Entity.User;
import com.ApoorvMathur.BurgerBackend.RequestClasses.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserData userData, HttpServletRequest httpServletRequest){
        String userId = (String)httpServletRequest.getAttribute("userId");
        if(userDAO.existsById(userId)){
            System.out.println("[RegisterUser] User " + userId + " logged in");
            return ResponseEntity.ok("User already registered");
        }
        User user = new User(userId, userData.getName(), userData.getEmail());
        System.out.println("[RegisterUser] User " + userId + " registered");
        userDAO.save(user);
        return ResponseEntity.ok("User added successfully");
    }
}
