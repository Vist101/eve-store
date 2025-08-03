package com.pionw.eve_store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OrderController {

    @GetMapping("/order")
    public String initRegion(Model model) {

        return "orderanalise";
    }
}
