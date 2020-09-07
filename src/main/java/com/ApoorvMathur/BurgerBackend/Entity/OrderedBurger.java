package com.ApoorvMathur.BurgerBackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "orderedburger")
public class OrderedBurger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salad")
    private int salad;

    @Column(name = "cheese")
    private int cheese;

    @Column(name = "sauce")
    private int sauce;

    @Column(name = "chicken")
    private int chicken;

    @Column(name = "alootikki")
    private int alootikki;

    @Column(name = "price")
    private float price;

    @Column(name = "onions")
    private int onions;

    @Column(name = "tomatoes")
    private int tomatoes;

    @Column(name = "mushrooms")
    private int mushrooms;

    @Column(name = "egg")
    private int egg;

    @Column(name = "mayonaise")
    private int mayonaise;
    @ManyToOne
    @JoinColumn(name = "orderid")
    @JsonIgnore
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderedBurger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salad=" + salad +
                ", cheese=" + cheese +
                ", sauce=" + sauce +
                ", chicken=" + chicken +
                ", alootikki=" + alootikki +
                ", price=" + price +
                ", onions=" + onions +
                ", tomatoes=" + tomatoes +
                ", mushrooms=" + mushrooms +
                ", egg=" + egg +
                ", mayonaise=" + mayonaise +
                ", order=" + order +
                '}';
    }
}
