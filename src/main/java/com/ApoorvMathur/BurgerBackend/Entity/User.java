package com.ApoorvMathur.BurgerBackend.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "imageurl")
    private String imageUrl;

    @OneToMany(mappedBy = "user")
//    @JsonIgnore
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        order.setUser(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", orders=" + orders +
                '}';
    }
}
