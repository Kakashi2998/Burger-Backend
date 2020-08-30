package com.ApoorvMathur.BurgerBackend.DAO;

import com.ApoorvMathur.BurgerBackend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface OrderDAO extends JpaRepository<Order, Integer> {
}
