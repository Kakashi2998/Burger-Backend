package com.ApoorvMathur.BurgerBackend.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "price")
    private float price;
}
