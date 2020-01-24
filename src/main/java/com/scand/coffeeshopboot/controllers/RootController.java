package com.scand.coffeeshopboot.controllers;

import com.scand.coffeeshopboot.security.AuthSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:" + AuthSuccessHandler.determineTargetUrl(authentication);
        }

        return "/login";
    }
}
