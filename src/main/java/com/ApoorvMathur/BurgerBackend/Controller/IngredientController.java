package com.ApoorvMathur.BurgerBackend.Controller;

import com.ApoorvMathur.BurgerBackend.DAO.IngredientDAO;
import com.ApoorvMathur.BurgerBackend.Entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientDAO ingredientDAO;

    @GetMapping("/get")
    public List<Ingredient> getIngredients(){
        return ingredientDAO.findAll();
    }
}
