package com.ApoorvMathur.BurgerBackend.Controller;

import com.ApoorvMathur.BurgerBackend.DAO.OrderDAO;
import com.ApoorvMathur.BurgerBackend.DAO.UserDAO;
import com.ApoorvMathur.BurgerBackend.Entity.Order;
import com.ApoorvMathur.BurgerBackend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping("")
    public List<User> getUsers(){
        return userDAO.findAll();
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getOrders(@PathVariable int userId){
        return userDAO.findById(userId).get().getOrders();
    }
}
