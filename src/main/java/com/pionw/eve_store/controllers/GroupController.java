package com.pionw.eve_store.controllers;

import com.pionw.eve_store.http_eve.ParseEveDate;
import com.pionw.eve_store.models.Group;
import com.pionw.eve_store.models.Item;
import com.pionw.eve_store.repo.GroupRepository;
import com.pionw.eve_store.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/setup/initgroup22")
    public String initGroup(Model model) {
        List<Group> group = ParseEveDate.getListGroups();
        groupRepository.saveAll(group);
        return "redirect:/";
    }
}
