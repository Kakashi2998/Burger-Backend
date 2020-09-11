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

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String Id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
//    @JsonIgnore
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        order.setUser(this);
    }

    public User() {
    }

    public User(String id, String name, String email) {
        this.Id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }
}
