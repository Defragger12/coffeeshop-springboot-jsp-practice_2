package com.scand.coffeeshopboot.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "coffees")
public class Coffee {

    public Coffee() {

    }

    public Coffee(String name, String description, BigDecimal price) {

        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Coffee(String name, String description, String imageUrl, BigDecimal price) {

        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(length = 10000)
    private String description;

    private String imageUrl;

    private BigDecimal price;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }
}