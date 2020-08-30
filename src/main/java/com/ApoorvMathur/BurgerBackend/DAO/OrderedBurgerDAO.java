package com.ApoorvMathur.BurgerBackend.DAO;

import com.ApoorvMathur.BurgerBackend.Entity.OrderedBurger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface OrderedBurgerDAO extends JpaRepository<OrderedBurger, Integer> {
}
