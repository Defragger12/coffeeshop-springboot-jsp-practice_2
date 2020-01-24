package com.scand.coffeeshopboot.dto;

public class CoffeeToConfirm {

    public CoffeeToConfirm() {

    }

    private String coffeeId;

    private String coffeeName;

    private String coffeePrice;

    private String coffeeImageUrl;

    private String totalQuantity;

    public CoffeeToConfirm(String coffeeId, String coffeeName, String coffeePrice, String coffeeImageUrl, String totalQuantity) {

        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
        this.coffeeImageUrl = coffeeImageUrl;
        this.totalQuantity = totalQuantity;
    }

    public String getCoffeeId() {

        return coffeeId;
    }

    public void setCoffeeId(String coffeeId) {

        this.coffeeId = coffeeId;
    }

    public String getCoffeeName() {

        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {

        this.coffeeName = coffeeName;
    }

    public String getCoffeePrice() {

        return coffeePrice;
    }

    public void setCoffeePrice(String coffeePrice) {

        this.coffeePrice = coffeePrice;
    }

    public String getCoffeeImageUrl() {

        return coffeeImageUrl;
    }

    public void setCoffeeImageUrl(String coffeeImageUrl) {

        this.coffeeImageUrl = coffeeImageUrl;
    }

    public String getTotalQuantity() {

        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {

        this.totalQuantity = totalQuantity;
    }
}
