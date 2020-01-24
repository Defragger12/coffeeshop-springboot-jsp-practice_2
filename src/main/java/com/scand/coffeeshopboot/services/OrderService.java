package com.scand.coffeeshopboot.services;

import com.scand.coffeeshopboot.domain.Order;
import com.scand.coffeeshopboot.domain.OrderItem;
import com.scand.coffeeshopboot.repository.CoffeeRepository;
import com.scand.coffeeshopboot.repository.OrderItemRepository;
import com.scand.coffeeshopboot.repository.OrderRepository;
import com.scand.coffeeshopboot.dto.CoffeeToConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.scand.coffeeshopboot.util.specs.OrderSpecs;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CoffeeRepository coffeeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CoffeeRepository coffeeRepository) {

        this.orderRepository = orderRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public Long finishOrder(
            String customerName,
            String customerPhone,
            String customerAddress,
            List<CoffeeToConfirm> pendingCoffeeOrder,
            BigDecimal totalOrderPrice
    ) {

        Order orderToSave = new Order(totalOrderPrice, customerName, customerAddress, customerPhone);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CoffeeToConfirm pendingCoffee : pendingCoffeeOrder) {
            orderItems.add(
                    new OrderItem(
                            coffeeRepository.findById(Long.valueOf(pendingCoffee.getCoffeeId())).get(),
                            orderToSave,
                            Integer.valueOf(pendingCoffee.getTotalQuantity())
                    )
            );
        }

        orderToSave.setItems(orderItems);

        Order savedOrder = orderRepository.save(orderToSave);

        return savedOrder.getId();
    }

    public Order makeOrderDelivered(Long orderId) {

        Optional<Order> orderToUpdate = orderRepository.findById(orderId);
        if (!orderToUpdate.isPresent()) {
            return null;
        }
        Order foundOrder = orderToUpdate.get();
        foundOrder.setIsCompleted(true);

        return orderRepository.save(foundOrder);
    }

    public Page getOrderList(Object searchIdString, Object searchCreatedDateFrom, Object searchCreatedDateTo, Pageable pageable) {

        Specification<Order> spec = where((Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> null);

        if (searchIdString != null && searchIdString != "") {
            spec = where(OrderSpecs.isIdLike((String) searchIdString));
        }
        if (searchCreatedDateFrom != null && searchCreatedDateFrom != "") {
            spec = spec.and(OrderSpecs.isCreatedDateLaterThan((String) searchCreatedDateFrom));
        }
        if (searchCreatedDateTo != null && searchCreatedDateTo != "") {
            spec = spec.and(OrderSpecs.isCreatedDateEarlierThan((String) searchCreatedDateTo));
        }

        return orderRepository.findAll(spec, pageable);
    }
}
