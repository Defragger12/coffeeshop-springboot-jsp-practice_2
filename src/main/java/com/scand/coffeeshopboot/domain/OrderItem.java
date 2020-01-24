package com.scand.coffeeshopboot.domain;

import javax.persistence.*;

@Entity
@Table(name="orderitems")
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public OrderItem() {

    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;

    public OrderItem(Coffee coffee, Order order, Integer quantity) {

        this.coffee = coffee;
        this.order = order;
        this.quantity = quantity;
    }

    @Override
    public String toString() {

        return "OrderItem{" +
                "coffee=" + coffee +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Coffee getCoffee() {

        return coffee;
    }

    public void setCoffee(Coffee coffee) {

        this.coffee = coffee;
    }

    public Order getOrder() {

        return order;
    }

    public void setOrder(Order order) {

        this.order = order;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }
}