package com.scand.coffeeshopboot.controllers;

import com.scand.coffeeshopboot.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaginationController {

    private final MessageUtil messageUtil;

    @Autowired
    public PaginationController(MessageUtil messageUtil) {

        this.messageUtil = messageUtil;
    }

    @Secured("ROLE_USER")
    @GetMapping(path = "/coffee/reset")
    public String reset(
            HttpSession session) {
            session.removeAttribute("coffee_searchName");
            session.removeAttribute("coffee_searchDescription");
            session.removeAttribute("coffee_minPrice");
            session.removeAttribute("coffee_maxPrice");
            session.removeAttribute("coffee_pageNumber");
            session.removeAttribute("coffee_sort");
            session.removeAttribute("coffee_pageSize");

        return "redirect:/coffee";
    }

    @Secured("ROLE_USER")
    @GetMapping(path = "/coffee/page")
    public String loadCoffeePage(
            HttpSession session,
            ModelMap model,
            @RequestParam(value = "coffee_searchName", required = false) String searchNameString,
            @RequestParam(value = "coffee_searchDescription", required = false) String searchDescription,
            @RequestParam(value = "coffee_minPrice", required = false) String minPrice,
            @RequestParam(value = "coffee_maxPrice", required = false) String maxPrice,
            @RequestParam(value = "coffee_pageNumber", required = false) String pageNumber,
            @RequestParam(value = "coffee_sort", required = false) String sort,
            @RequestParam(value = "coffee_pageSize", required = false) String pageSize) {

        List<String> errors = new ArrayList<>();

        if (searchNameString != null) {
            session.setAttribute("coffee_searchName", searchNameString);
        }

        if (searchDescription != null) {
            session.setAttribute("coffee_searchDescription", searchDescription);
        }

        if (minPrice == null || minPrice.equals("")) {
            session.setAttribute("coffee_minPrice", null);
        } else {
            if (NumberUtils.isNumber(minPrice) && Integer.valueOf(minPrice) >= 0) {
                session.setAttribute("coffee_minPrice", minPrice);
            } else {
                errors.add(messageUtil.getMessage("error.minPrice"));
            }
        }

        if (maxPrice != null && !maxPrice.equals("")) {
            session.setAttribute("coffee_minPrice", maxPrice);
        } else {
            if (NumberUtils.isNumber(maxPrice) && Integer.valueOf(maxPrice) >= 0) {
                session.setAttribute("coffee_maxPrice", maxPrice);
            } else {
                errors.add(messageUtil.getMessage("error.maxPrice"));
            }
        }

        if (!NumberUtils.isNumber(pageNumber)) {
            session.setAttribute("coffee_pageNumber", "1");
        } else {
            session.setAttribute("coffee_pageNumber", pageNumber);
        }

        if (sort != null && (sort.contains(",asc") || sort.contains(",desc"))) {
            session.setAttribute("coffee_sort", sort);
        }

        if (StringUtils.isNumeric(pageSize)) {
            session.setAttribute("coffee_pageSize", pageSize);
        }

        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "coffeeList";
        }

        return "redirect:/coffee";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/order/page")
    public String loadOrderPage(
            HttpSession session,
            @RequestParam(value = "order_searchId", required = false) String searchIdString,
            @RequestParam(value = "order_searchDateFrom", required = false) String searchDateFrom,
            @RequestParam(value = "order_searchDateTo", required = false) String searchDateTo,
            @RequestParam(value = "order_pageNumber", required = false) String pageNumber,
            @RequestParam(value = "order_sort", required = false) String sort,
            @RequestParam(value = "order_pageSize", required = false) String pageSize
    ) {

        if (searchIdString != null) {
            session.setAttribute("order_searchId", searchIdString);
        }

        if (searchDateFrom != null) {
            session.setAttribute("order_searchDateFrom", searchDateFrom);
        }

        if (searchDateTo != null) {
            session.setAttribute("order_searchDateTo", searchDateTo);
        }

        if (!NumberUtils.isNumber(pageNumber)) {
            session.setAttribute("order_pageNumber", "1");
        } else {
            session.setAttribute("order_pageNumber", pageNumber);
        }

        if (sort != null && (sort.contains(",asc") || sort.contains(",desc"))) {
            session.setAttribute("order_sort", sort);
        }

        if (NumberUtils.isNumber(pageSize)) {
            session.setAttribute("order_pageSize", pageSize);
        }

        return "redirect:/admin";
    }
}