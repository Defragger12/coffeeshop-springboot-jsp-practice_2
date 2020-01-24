package com.scand.coffeeshopboot.controllers;

import com.scand.coffeeshopboot.domain.Coffee;
import com.scand.coffeeshopboot.services.CoffeeService;
import com.scand.coffeeshopboot.services.OrderService;
import com.scand.coffeeshopboot.dto.CoffeeToConfirm;
import com.scand.coffeeshopboot.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;


@Controller
public class OrderController {

    private final OrderService orderService;

    private final CoffeeService coffeeService;

    private final MessageUtil messageUtil;

    @Autowired
    public OrderController(OrderService orderService, CoffeeService coffeeService, MessageUtil messageUtil) {

        this.orderService = orderService;
        this.coffeeService = coffeeService;
        this.messageUtil = messageUtil;
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/order/add-to-cart", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> addToCart(
            HttpSession session,
            @RequestParam("coffeeId") String coffeeId,
            @RequestParam("quantityToAdd") String quantityToAdd
    ) {

        Coffee selectedCoffee = coffeeService.getCoffeeById(coffeeId);

        if (selectedCoffee == null) {
            return new ResponseEntity<>(messageUtil.getMessage("error.invalidCoffeeId", coffeeId), HttpStatus.BAD_REQUEST);
        }


        if (!StringUtils.isNumeric(quantityToAdd) || Integer.valueOf(quantityToAdd) > 10000) {
            return new ResponseEntity<>(messageUtil.getMessage("error.invalidQuantityValue"), HttpStatus.BAD_REQUEST);
        }

        List<CoffeeToConfirm> updatedCoffees = coffeeService.addCoffeeToList(
                (List<CoffeeToConfirm>) session.getAttribute("coffeesToConfirm"),
                new CoffeeToConfirm(
                        String.valueOf(selectedCoffee.getId()),
                        selectedCoffee.getName(),
                        String.valueOf(selectedCoffee.getPrice()),
                        selectedCoffee.getImageUrl(),
                        quantityToAdd
                )
        );

        String finalPrice = coffeeService.getUpdatedPrice(updatedCoffees);
        Integer totalQuantity = coffeeService.getTotalQuantity(updatedCoffees);

        session.setAttribute("coffeesToConfirm", updatedCoffees);
        session.setAttribute("finalPrice", finalPrice);
        session.setAttribute("totalQuantity", totalQuantity);

        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("finalPrice", finalPrice);
        attributesMap.put("totalQuantity", String.valueOf(totalQuantity));

        return new ResponseEntity<>(String.valueOf(new JSONObject(attributesMap)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/order/action", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> performItemsAction(
            HttpSession session,
            @RequestParam("coffeeId") String coffeeId,
            @RequestParam("action") String action
    ) {

        List<CoffeeToConfirm> updatedCoffees = coffeeService.performCoffeeAction(
                (List<CoffeeToConfirm>) session.getAttribute("coffeesToConfirm"),
                action,
                coffeeId
        );

        String finalPrice = coffeeService.getUpdatedPrice(updatedCoffees);
        Integer totalQuantity = coffeeService.getTotalQuantity(updatedCoffees);

        session.setAttribute("coffeesToConfirm", updatedCoffees);
        session.setAttribute("finalPrice", finalPrice);
        session.setAttribute("totalQuantity", totalQuantity);

        Map<String, String> attributesMap = new HashMap<>();
        attributesMap.put("finalPrice", finalPrice);
        attributesMap.put("totalQuantity", String.valueOf(totalQuantity));

        return attributesMap;
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String showOrder() {

        return "order";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
    public String confirmOrder(
            @SessionAttribute(value = "coffeesToConfirm", required = false) List<CoffeeToConfirm> pendingOrder) {

        if (pendingOrder == null || pendingOrder.size() == 0) {
            return "redirect: /coffee";
        }

        return "confirmOrder";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/order/confirm", method = RequestMethod.POST)
    public String finishOrder(
            ModelMap model,
            HttpSession session,
            @RequestParam(value = "customer_name", required = false) String customerName,
            @RequestParam(value = "customer_phone", required = false) String customerPhone,
            @RequestParam(value = "customer_address", required = false) String customerAddress,
            @SessionAttribute("coffeesToConfirm") List<CoffeeToConfirm> pendingOrder,
            @SessionAttribute("finalPrice") String finalPrice
    ) {

        List<String> errors = new ArrayList<>();

        if (customerPhone == null || customerPhone.equals("") || !customerPhone.matches("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$")) {
            errors.add(messageUtil.getMessage("error.invalidPhoneNumber"));
        }

        if (errors.size() != 0) {
            model.addAttribute("errors", errors);
            model.addAttribute("customer_name", customerName);
            model.addAttribute("customer_phone", customerPhone);
            model.addAttribute("customer_address", customerAddress);
            return "confirmOrder";
        }

        Long orderId = orderService.finishOrder(customerName, customerPhone, customerAddress, pendingOrder, new BigDecimal(finalPrice));

        session.removeAttribute("finalPrice");
        session.removeAttribute("coffeesToConfirm");
        session.removeAttribute("totalQuantity");

        model.addAttribute("orderId", orderId);

        return "finish";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/order/{id}/delivered", method = RequestMethod.GET)
    public @ResponseBody String deliverOrder(@PathVariable("id") Long orderId) {

        return orderService.makeOrderDelivered(orderId).getId().toString();
    }
}
