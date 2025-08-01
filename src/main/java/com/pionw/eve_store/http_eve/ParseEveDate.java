package com.pionw.eve_store.http_eve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pionw.eve_store.models.Category;
import com.pionw.eve_store.models.Group;
import com.pionw.eve_store.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ParseEveDate {
    private static List<Long> getListEveIdItem() {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String eveId = ReadTextFromURL.readString(bundle.getString("eve.itemid"));
        String number = "";
        for (int i = 1; i < eveId.length(); i++) {
            boolean flagEnd = false;
            if ((eveId.charAt(i) == ',') || (eveId.charAt(i) == ']')) {
                flagEnd = true;
            }
            if (flagEnd) {
                list.add(Long.parseLong(number));
                number = "";
            } else {
                number += eveId.charAt(i);
            }
        }
        return list;
    }

    private static List<Long> getListEveIdGroup() {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String eveId = ReadTextFromURL.readString(bundle.getString("eve.groupid"));
        String number = "";
        for (int i = 1; i < eveId.length(); i++) {
            boolean flagEnd = false;
            if ((eveId.charAt(i) == ',') || (eveId.charAt(i) == ']')) {
                flagEnd = true;
            }
            if (flagEnd) {
                list.add(Long.parseLong(number));
                number = "";
            } else {
                number += eveId.charAt(i);
            }
        }
        return list;
    }

    private static List<Long> getListEveIdCategory() {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String eveId = ReadTextFromURL.readString(bundle.getString("eve.categoryid"));
        String number = "";
        for (int i = 1; i < eveId.length(); i++) {
            boolean flagEnd = false;
            if ((eveId.charAt(i) == ',') || (eveId.charAt(i) == ']')) {
                flagEnd = true;
            }
            if (flagEnd) {
                list.add(Long.parseLong(number));
                number = "";
            } else {
                number += eveId.charAt(i);
            }
        }
        return list;
    }

    private static Item conversionItem(String eveItemString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(eveItemString, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Item item = new Item();
        Object result = map.get("type_id");
        item.setEveId((long) (int) result);
        if (map.get("description") != null) {
            item.setDescription((String) map.get("description"));
        } else {
            item.setDescription("No Description");
        }
        item.setName((String) map.get("name"));
        item.setGroup_id((long) (int) map.get("group_id"));
        if (map.get("icon_id") != null){
            item.setIcon_id((long) (int) map.get("icon_id"));
        } else {
            item.setIcon_id(0L);
        }
        item.setVolume((float) (double) map.get("volume"));
        item.setPackaged_volume((float) (double) map.get("packaged_volume"));
        item.setPublished((boolean) map.get("published"));

        return item;
    }

    public static List<Item> getListEveItems() {
        List<Long> listEveIdItem = getListEveIdItem();
        List<Item> items = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");

        for (Long id : listEveIdItem) {
            String eveItem = bundle.getString("eve.getitem");
            eveItem = eveItem.replace("*", "" + id);
            String eveItemString = ReadTextFromURL.readString(eveItem);
            items.add(conversionItem(eveItemString));
        }
        return items;
    }

    private static Group conversionGroup(String eveGroupString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(eveGroupString, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Group group = new Group();
        Object result = map.get("group_id");
        group.setGroup_id((long) (int) result);
        group.setDescription("Description");
        group.setName((String) map.get("name"));
        group.setPublished((boolean) map.get("published"));
        group.setCategory_id((long)(int) map.get("category_id"));

        ArrayList<Integer> groupIds = new ArrayList<>();
        groupIds.addAll((ArrayList<Integer>) map.get("types"));
        group.setTypes(groupIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));

        return group;
    }

    public static List<Group> getListGroups() {
        List<Long> listEveIdGroup = getListEveIdGroup();
        List<Group> groups = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");

        for (Long id : listEveIdGroup) {
            String eveGroup = bundle.getString("eve.getgroup");
            eveGroup = eveGroup.replace("*", "" + id);
            String eveGroupString = ReadTextFromURL.readString(eveGroup);
            groups.add(conversionGroup(eveGroupString));
        }
        return groups;
    }

    private static Category conversionCategory(String eveCategoryString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(eveCategoryString, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Category category = new Category();
        Object result = map.get("category_id");
        category.setCategoryId((long) (int) result);
        category.setDescription("Description");
        category.setName((String) map.get("name"));
        category.setPublished((boolean) map.get("published"));

        ArrayList<Integer> categoryIds = new ArrayList<>();
        categoryIds.addAll((ArrayList<Integer>) map.get("groups"));
        category.setGroups_txt(categoryIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));

        return category;
    }

    public static List<Category> getListCategory() {
        List<Long> listEveIdCategories = getListEveIdCategory();
        List<Category> categories = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");

        for (Long id : listEveIdCategories) {
            String eveCategory = bundle.getString("eve.getcategory");
            eveCategory = eveCategory.replace("*", "" + id);
            String eveCategoryString = ReadTextFromURL.readString(eveCategory);
            categories.add(conversionCategory(eveCategoryString));
        }
        return categories;
    }

}

