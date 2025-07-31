package com.pionw.eve_store.controllers;

import com.pionw.eve_store.models.Item;
import com.pionw.eve_store.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/item")
    public String items(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "item";
    }
}
