package com.pionw.eve_store.controllers;

import com.pionw.eve_store.http_eve.ParseEveDate;
import com.pionw.eve_store.models.Group;
import com.pionw.eve_store.models.Region;
import com.pionw.eve_store.repo.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/setup/initregion")
    public String initRegion(Model model) {
        List<Region> regions = ParseEveDate.getListRegion();
        regionRepository.saveAll(regions);
        return "redirect:/";
    }
}
