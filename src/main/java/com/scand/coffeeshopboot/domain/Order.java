package com.scand.coffeeshopboot.domain;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderItem> items;

    private BigDecimal price;

    private Boolean isCompleted = false;

    private String customerName;

    private String customerAddress;

    private String customerPhone;

    @Column(columnDefinition = "timestamp default now()")
    private Date createdDate = new Date();

    public Order() {

    }

    public Order(BigDecimal price,String customerName, String customerAddress, String customerPhone) {

        this.price = price;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public Order(List<OrderItem> items, BigDecimal price, String customerName, String customerAddress, String customerPhone) {

        this.items = items;
        this.price = price;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public Order(List<OrderItem> items, BigDecimal price) {

        this.items = items;
        this.price = price;
        isCompleted = false;
    }

    @Override
    public String toString() {

        return "Order{" +
                "price=" + price +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public List<OrderItem> getItems() {

        return items;
    }

    public void setItems(List<OrderItem> items) {

        this.items = items;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public Boolean getIsCompleted() {

        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {

        isCompleted = completed;
    }

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    public String getCustomerAddress() {

        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {

        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {

        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {

        this.customerPhone = customerPhone;
    }

    public Date getCreatedDate() {

        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {

        this.createdDate = createdDate;
    }
}