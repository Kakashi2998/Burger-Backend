package com.ApoorvMathur.BurgerBackend.Controller;

import com.ApoorvMathur.BurgerBackend.DAO.OrderDAO;
import com.ApoorvMathur.BurgerBackend.DAO.OrderedBurgerDAO;
import com.ApoorvMathur.BurgerBackend.DAO.UserDAO;
import com.ApoorvMathur.BurgerBackend.Entity.Order;
import com.ApoorvMathur.BurgerBackend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderedBurgerDAO orderedBurgerDAO;

    @PostMapping("/{userId}/order")
    private ResponseEntity<String> order(@PathVariable int userId, @RequestBody Order order){
        User user = userDAO.getOne(userId);
        user.addOrder(order);
        orderDAO.save(order);
        order.getOrderedBurgers().forEach(orderedBurger -> {
            orderedBurger.setOrder(order);
            orderedBurgerDAO.save(orderedBurger);
        });
        return ResponseEntity.ok("Order successfully taken!");
    }
}
