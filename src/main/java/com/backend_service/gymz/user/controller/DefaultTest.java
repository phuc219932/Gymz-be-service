package com.backend_service.gymz.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultTest {
    @GetMapping("/test")
    @ResponseBody
    public String index() {
        return "Home page";
    }
}