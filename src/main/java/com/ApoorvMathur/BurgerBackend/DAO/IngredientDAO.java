package com.ApoorvMathur.BurgerBackend.DAO;

import com.ApoorvMathur.BurgerBackend.Entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {
}
