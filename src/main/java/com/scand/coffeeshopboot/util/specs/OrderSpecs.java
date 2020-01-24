package com.scand.coffeeshopboot.util.specs;

import com.scand.coffeeshopboot.domain.Order;
import com.scand.coffeeshopboot.domain.Order_;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;

public class OrderSpecs {
    public static Specification<Order> isIdLike(String searchIdString) {
        return (Specification<Order>) (root, query, builder) -> builder.like(
                root.get(Order_.ID).as(String.class), "%" + searchIdString + "%"
        );
    }

    public static Specification<Order> isCreatedDateLaterThan(String searchCreatedDateString) {
        return (Specification<Order>) (root, query, builder) -> builder.greaterThanOrEqualTo(
                root.get(Order_.CREATED_DATE), Date.valueOf(searchCreatedDateString)
        );
    }

    public static Specification<Order> isCreatedDateEarlierThan(String searchCreatedDateString) {
        return (Specification<Order>) (root, query, builder) -> builder.lessThanOrEqualTo(
                root.get(Order_.CREATED_DATE), Date.valueOf(searchCreatedDateString)
        );
    }
}
