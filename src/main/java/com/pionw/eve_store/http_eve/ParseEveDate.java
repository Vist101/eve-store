package com.pionw.eve_store.http_eve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pionw.eve_store.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ParseEveDate {
    private static List<Long> getListEveIdItem() {
        List<Long> list = new ArrayList<Long>();
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String eveId = ReadTextFromURL.readString(bundle.getString("eve.itemid"));
        String number = "";
        for (int i = 1; i < eveId.length() - 1; i++) {
            boolean flagEnd = false;
            if (eveId.charAt(i) == ',') {
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
        item.setDescription((String) map.get("description"));
        item.setName((String) map.get("name"));
        item.setGroup_id((long) (int) map.get("group_id"));
        if (map.get("icon_id") != null){
            item.setIcon_id((long) (int) map.get("icon_id"));
        } else {
            item.setIcon_id(0L);
        }
        item.setVolume((float) (double) map.get("volume"));
        item.setPackaged_volume((float) (double) map.get("packaged_volume"));

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

}

