package com.scand.coffeeshopboot.controllers;

import com.scand.coffeeshopboot.services.CoffeeService;
import com.scand.coffeeshopboot.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {

        this.coffeeService = coffeeService;
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/coffee", method = RequestMethod.GET)
    public String displayCoffees(
            ModelMap model,
            HttpSession session,
            @SessionAttribute(value = "coffee_searchName", required = false) String searchNameString,
            @SessionAttribute(value = "coffee_searchDescription", required = false) String searchDescription,
            @SessionAttribute(value = "coffee_minPrice", required = false) String minPrice,
            @SessionAttribute(value = "coffee_maxPrice", required = false) String maxPrice,
            @SessionAttribute(value = "coffee_pageNumber", required = false) String pageNumber,
            @SessionAttribute(value = "coffee_sort", required = false) String sort,
            @SessionAttribute(value = "coffee_pageSize", required = false) String pageSize
    ) {

        if (pageNumber == null) {
            pageNumber = PaginationUtil.DEFAULT_PAGE_NUMBER;
            session.setAttribute("coffee_pageNumber", PaginationUtil.DEFAULT_PAGE_NUMBER);
        }
        if (sort == null) {
            sort = PaginationUtil.DEFAULT_COFFEE_SORT;
            session.setAttribute("coffee_sort", PaginationUtil.DEFAULT_COFFEE_SORT);
        }
        if (pageSize == null) {
            pageSize = PaginationUtil.DEFAULT_PAGE_SIZE;
            session.setAttribute("coffee_pageSize", PaginationUtil.DEFAULT_PAGE_SIZE);
        }

        Pageable pageable = PaginationUtil.convertToPageRequest(sort, pageNumber, pageSize);
        Page coffeePage = coffeeService.getCoffeeList(searchNameString, searchDescription, minPrice, maxPrice, pageable);

        if (pageNumber.equals(PaginationUtil.DEFAULT_PAGE_NUMBER)) {
            session.setAttribute("coffee_pageCount", coffeePage.getTotalPages());
            session.setAttribute("coffee_totalElementsCount", coffeePage.getTotalElements());
        }

        model.addAttribute("coffeeList", coffeePage.getContent());

        return "coffeeList";
    }


    @RequestMapping(value = "/coffee/{id}", method = RequestMethod.GET)
    public String displayCoffeeInfo(ModelMap model, @PathVariable("id") String coffeeId) {

        model.addAttribute("coffee", coffeeService.getCoffeeById(coffeeId));

        return "coffeeInfo";
    }
}
