package com.pionw.eve_store.controllers;

import com.pionw.eve_store.http_eve.ParseEveDate;
import com.pionw.eve_store.models.Category;
import com.pionw.eve_store.models.Group;
import com.pionw.eve_store.repo.CategoryRepository;
import com.pionw.eve_store.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/setup/initcategory")
    public String initCategory(Model model) {
        List<Category> categories = ParseEveDate.getListCategory();
        categoryRepository.saveAll(categories);
        return "redirect:/";
    }
}
