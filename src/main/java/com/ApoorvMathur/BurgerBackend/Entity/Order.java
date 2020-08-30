package com.ApoorvMathur.BurgerBackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "totalprice")
    private float totalPrice;

    @Column(name = "delivered")
    private boolean delivered;

    @OneToMany(mappedBy = "order")
    private List<OrderedBurger> orderedBurgers;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

    public List<OrderedBurger> getOrderedBurgers() {
        return orderedBurgers;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", delivered=" + delivered +
                ", orderedBurgers=" + orderedBurgers +
                ", user=" + user +
                '}';
    }
}
