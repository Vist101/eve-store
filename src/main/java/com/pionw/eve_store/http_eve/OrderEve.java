package com.pionw.eve_store.http_eve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pionw.eve_store.db.entity.Order;

import java.util.List;
import java.util.ResourceBundle;

public class OrderEve {

    private String readStringOrders(Long marketId,Long itemId,boolean isSell){
        ResourceBundle bundle = ResourceBundle.getBundle("eve_urls/urls");
        String eveOrders = bundle.getString("eve.orders");
        eveOrders = eveOrders.replace("*", "" + itemId);
        eveOrders = eveOrders.replace("marketid", "" + marketId);
        if(isSell) {
            eveOrders = eveOrders.replace("sell_buy", "sell");
        } else {
            eveOrders = eveOrders.replace("sell_sell", "buy");
        }
        return ReadTextFromURL.readString(eveOrders);
    }

    public List<Order> getOrders(Long marketId,Long itemId,boolean isSell){

        String json = readStringOrders(marketId,itemId,isSell);
        ObjectMapper mapper = new ObjectMapper();
        List<Order> orders = null;
        try {
            orders = mapper.readValue(json, new TypeReference<List<Order>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }



    public static void main(String[] args) {

        OrderEve orderEve = new OrderEve();
        orderEve.getOrders(10000002L,3725L,true);
    }
}
