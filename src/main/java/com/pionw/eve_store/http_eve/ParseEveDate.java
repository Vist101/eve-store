package com.pionw.eve_store.http_eve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pionw.eve_store.models.Category;
import com.pionw.eve_store.models.Group;
import com.pionw.eve_store.models.Item;
import com.pionw.eve_store.models.Region;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ParseEveDate {


    private static List<Long> getListEveIdItem(int page) {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String url = bundle.getString("eve.itemid");
        url = url.replace("*","" + page);
        String eveId = ReadTextFromURL.readString(url);
        String number = "";
        if (!eveId.isEmpty()) {
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
        }
        return list;
    }

    private static List<Long> getListEveIdGroup(int page) {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String url = bundle.getString("eve.groupid");
        url = url.replace("*","" + page);
        String groupId = ReadTextFromURL.readString(url);
        String number = "";
        if (!groupId.isEmpty()) {
            for (int i = 1; i < groupId.length(); i++) {
                boolean flagEnd = false;
                if ((groupId.charAt(i) == ',') || (groupId.charAt(i) == ']')) {
                    flagEnd = true;
                }
                if (flagEnd) {
                    list.add(Long.parseLong(number));
                    number = "";
                } else {
                    number += groupId.charAt(i);
                }
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

    private static List<Long> getListEveIdRegion() {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String url = bundle.getString("eve.regionid");
        String eveId = ReadTextFromURL.readString(url);
        String number = "";
        if (!eveId.isEmpty()) {
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
        if (map.get("icon_id") != null) {
            item.setIcon_id((long) (int) map.get("icon_id"));
        } else {
            item.setIcon_id(0L);
        }
        item.setVolume((float) (double) map.get("volume"));
        item.setPackaged_volume((float) (double) map.get("packaged_volume"));
        item.setPublished((boolean) map.get("published"));

        return item;
    }

    public static List<Item> getListEveItems(int page, boolean isPublished) {
        List<Long> listEveIdItem = getListEveIdItem(page);
        List<Item> items = new ArrayList<>();
        if (!listEveIdItem.isEmpty()) {
            ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
            for (Long id : listEveIdItem) {
                String eveItem = bundle.getString("eve.getitem");
                eveItem = eveItem.replace("*", "" + id);
                String eveItemString = ReadTextFromURL.readString(eveItem);
                Item item = conversionItem(eveItemString);
                if (isPublished) {
                    if (item.getPublished()) {
                        items.add(item);
                    }
                } else {
                    items.add(item);
                }
            }
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
        group.setCategory_id((long) (int) map.get("category_id"));

        ArrayList<Integer> groupIds = new ArrayList<>();
        groupIds.addAll((ArrayList<Integer>) map.get("types"));
        group.setTypes(groupIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));

        return group;
    }

    public static List<Group> getListGroups(int page, boolean isPublished) {
        List<Long> listEveIdGroup = getListEveIdGroup(page);
        List<Group> groups = new ArrayList<>();
        if (!listEveIdGroup.isEmpty()) {
            ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");

            for (Long id : listEveIdGroup) {
                String eveGroup = bundle.getString("eve.getgroup");
                eveGroup = eveGroup.replace("*", "" + id);
                String eveGroupString = ReadTextFromURL.readString(eveGroup);
                Group group = conversionGroup(eveGroupString);
                if (isPublished) {
                    if (group.getPublished()) {
                        groups.add(group);
                    }
                }else {
                    groups.add(group);
                }
            }
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

    private static Region conversionRegion(String eveRegionString) {

        JSONObject obj = new JSONObject(eveRegionString);
        Region region = new Region();
        region.setName(obj.getString("name"));
        region.setRegionId(obj.getLong("region_id"));
        if (obj.has("description")) {
            region.setDescription(obj.getString("description"));
        }else {
            region.setDescription("");
        }
        region.setConstellations(obj.getJSONArray("constellations").toString());
        return region;
    }

    public static List<Region> getListRegion() {
        List<Long> listEveIdRegion = getListEveIdRegion();
        List<Region> regiones = new ArrayList<>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");

        for (Long id : listEveIdRegion) {
            String eveRegion = bundle.getString("eve.getregion");
            eveRegion = eveRegion.replace("*", "" + id);
            String eveRegionString = ReadTextFromURL.readString(eveRegion);
            regiones.add(conversionRegion(eveRegionString));
        }
        return regiones;
    }


}

