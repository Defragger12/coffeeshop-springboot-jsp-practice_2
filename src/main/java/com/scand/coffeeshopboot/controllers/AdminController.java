package com.scand.coffeeshopboot.controllers;

import com.scand.coffeeshopboot.services.OrderService;
import com.scand.coffeeshopboot.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class AdminController {

    private final OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService) {

        this.orderService = orderService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayOrders(
            ModelMap model,
            HttpSession session,
            @SessionAttribute(value = "order_searchId", required = false) String searchIdString,
            @SessionAttribute(value = "order_searchDateFrom", required = false) String searchDateFrom,
            @SessionAttribute(value = "order_searchDateTo", required = false) String searchDateTo,
            @SessionAttribute(value = "order_pageNumber", required = false) String pageNumber,
            @SessionAttribute(value = "order_sort", required = false) String sort,
            @SessionAttribute(value = "order_pageSize", required = false) String pageSize
    ) {

        if (pageNumber == null) {
            pageNumber = PaginationUtil.DEFAULT_PAGE_NUMBER;
            session.setAttribute("order_pageNumber", PaginationUtil.DEFAULT_PAGE_NUMBER);
        }
        if (sort == null) {
            sort = PaginationUtil.DEFAULT_ORDER_SORT;
            session.setAttribute("order_sort", PaginationUtil.DEFAULT_ORDER_SORT);
        }
        if (pageSize == null) {
            pageSize = PaginationUtil.DEFAULT_PAGE_SIZE;
            session.setAttribute("order_pageSize", PaginationUtil.DEFAULT_PAGE_SIZE);
        }

        Pageable pageable = PaginationUtil.convertToPageRequest(sort, pageNumber, pageSize);
        Page orderPage = orderService.getOrderList(searchIdString, searchDateFrom, searchDateTo, pageable);

        if (pageNumber.equals(PaginationUtil.DEFAULT_PAGE_NUMBER)) {
            session.setAttribute("order_pageCount", orderPage.getTotalPages());
            session.setAttribute("order_totalElementsCount", orderPage.getTotalElements());
        }

        model.addAttribute("orderList", orderPage.getContent());

        return "admin";
    }
}