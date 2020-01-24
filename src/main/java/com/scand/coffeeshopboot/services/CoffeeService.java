package com.scand.coffeeshopboot.services;

import com.scand.coffeeshopboot.domain.Coffee;
import com.scand.coffeeshopboot.repository.CoffeeRepository;
import com.scand.coffeeshopboot.dto.CoffeeToConfirm;
import com.scand.coffeeshopboot.util.specs.CoffeeSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {

        this.coffeeRepository = coffeeRepository;
    }

    public Page getCoffeeList(Object searchNameString, Object searchDescription, Object minPrice, Object maxPrice, Pageable pageable) {

        Specification<Coffee> spec = where((Specification<Coffee>) (root, criteriaQuery, criteriaBuilder) -> null);

        if (searchNameString != null && searchNameString != "") {
            spec = where(CoffeeSpecs.isNameLike((String) searchNameString));
        }
        if (searchDescription != null && searchDescription != "") {
            spec = spec.and(CoffeeSpecs.isDescriptionLike((String) searchDescription));
        }
        if (minPrice != null && minPrice != "") {
            spec = spec.and(CoffeeSpecs.isPriceMoreThan((String) minPrice));
        }
        if (maxPrice != null && maxPrice != "") {
            spec = spec.and(CoffeeSpecs.isPriceLessThan((String) maxPrice));
        }

        return coffeeRepository.findAll(spec, pageable);
    }

    public Coffee getCoffeeById(String id) {

        Optional<Coffee> coffee = coffeeRepository.findById(Long.valueOf(id));
        return coffee.orElse(null);

    }

    public List<CoffeeToConfirm> addCoffeeToList(List<CoffeeToConfirm> existingCoffees, CoffeeToConfirm coffeeToAdd) {

        if (existingCoffees == null) {
            existingCoffees = new ArrayList<>();
        } else {

            for (CoffeeToConfirm coffeeToConfirm : existingCoffees) {
                if (coffeeToConfirm.getCoffeeId().equals(coffeeToAdd.getCoffeeId())) {
                    coffeeToConfirm.setTotalQuantity(
                            String.valueOf(
                                    Integer.valueOf(coffeeToConfirm.getTotalQuantity()) + Integer.valueOf(coffeeToAdd.getTotalQuantity())
                            )
                    );
                    return existingCoffees;
                }
            }
        }

        existingCoffees.add(coffeeToAdd);

        return existingCoffees;
    }

    public List<CoffeeToConfirm> performCoffeeAction(List<CoffeeToConfirm> existingCoffees, String action, String coffeeId) {

        if (existingCoffees == null) {
            return null;
        }

        Optional<CoffeeToConfirm> affectedCoffeeOptional = existingCoffees.stream().filter(coffee -> coffee.getCoffeeId().equals(coffeeId)).findFirst();

        if (!affectedCoffeeOptional.isPresent()) {
            return null;
        }

        CoffeeToConfirm affectedCoffee = affectedCoffeeOptional.get();

        switch (action) {
            case "addItem":
                affectedCoffee.setTotalQuantity(String.valueOf(Integer.valueOf(affectedCoffee.getTotalQuantity()) + 1));
                break;
            case "removeItem":
                affectedCoffee.setTotalQuantity(String.valueOf(Integer.valueOf(affectedCoffee.getTotalQuantity()) - 1));
                if (affectedCoffee.getTotalQuantity().equals("0")) {
                    existingCoffees.remove(affectedCoffee);
                }
                break;
            case "removeCoffee":
                existingCoffees.remove(affectedCoffee);
                break;
        }

        return existingCoffees;
    }

    public String getUpdatedPrice(List<CoffeeToConfirm> coffeesToConfirm) {

        BigDecimal updatedPrice = new BigDecimal(0);

        for (CoffeeToConfirm coffeeToConfirm : coffeesToConfirm) {
            updatedPrice = updatedPrice
                    .add(new BigDecimal(coffeeToConfirm.getCoffeePrice())
                            .multiply(new BigDecimal(coffeeToConfirm.getTotalQuantity())));
        }

        return updatedPrice.toString();
    }

    public Integer getTotalQuantity(List<CoffeeToConfirm> coffeesToConfirm) {

        Integer totalQuantity = 0;
        for (CoffeeToConfirm coffeeToConfirm : coffeesToConfirm) {
            totalQuantity += Integer.valueOf(coffeeToConfirm.getTotalQuantity());
        }

        return totalQuantity;
    }
}
