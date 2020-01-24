package com.scand.coffeeshopboot.util.specs;

import com.scand.coffeeshopboot.domain.Coffee;
import com.scand.coffeeshopboot.domain.Coffee_;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CoffeeSpecs {

    public static Specification<Coffee> isNameLike(String searchNameString) {
        return (Specification<Coffee>) (root, query, builder) -> builder.like(
                root.get(Coffee_.name), "%" + searchNameString + "%"
        );
    }

    public static Specification<Coffee> isDescriptionLike(String searchDescriptionString) {
        return (Specification<Coffee>) (root, query, builder) -> builder.like(
                root.get(Coffee_.description), "%" + searchDescriptionString + "%"
        );
    }

    public static Specification<Coffee> isPriceMoreThan(String minPriceString) {
        return (Specification<Coffee>) (root, query, builder) -> builder.greaterThan(
                root.get(Coffee_.price), new BigDecimal(minPriceString)
        );
    }

    public static Specification<Coffee> isPriceLessThan(String maxPriceString) {
        return (Specification<Coffee>) (root, query, builder) -> builder.lessThan(
                root.get(Coffee_.price), new BigDecimal(maxPriceString)
        );
    }
}
