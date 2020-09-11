package com.ApoorvMathur.BurgerBackend.Controller;

import com.ApoorvMathur.BurgerBackend.DAO.OrderDAO;
import com.ApoorvMathur.BurgerBackend.DAO.OrderedBurgerDAO;
import com.ApoorvMathur.BurgerBackend.DAO.UserDAO;
import com.ApoorvMathur.BurgerBackend.Entity.Order;
import com.ApoorvMathur.BurgerBackend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderedBurgerDAO orderedBurgerDAO;

    @PostMapping("/post")
    private ResponseEntity<String> order(@RequestBody Order order, HttpServletRequest httpServletRequest){
        String userId = (String)httpServletRequest.getAttribute("userId");
        User user = userDAO.getOne(userId);
        user.addOrder(order);
        orderDAO.save(order);
        order.getOrderedBurgers().forEach(orderedBurger -> {
            orderedBurger.setOrder(order);
            orderedBurgerDAO.save(orderedBurger);
        });
        return ResponseEntity.ok("Order successfully taken!");
    }

    @GetMapping("/get")
    public List<Order> getOrders(HttpServletRequest httpServletRequest){
        String userId = (String)httpServletRequest.getAttribute("userId");
        return userDAO.findById(userId).get().getOrders();
    }
}
