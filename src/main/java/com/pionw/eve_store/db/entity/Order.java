package com.pionw.eve_store.db.entity;

public class Order {
    private int duration;
    private boolean is_buy_order;
    private String issued;
    private Long location_id;
    private Integer min_volume;
    private Long order_id;
    private Float price;
    private String range;
    private Long system_id;
    private Long type_id;
    private int volume_remain;
    private int volume_total;

    public Order() {
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isIs_buy_order() {
        return is_buy_order;
    }

    public void setIs_buy_order(boolean is_buy_order) {
        this.is_buy_order = is_buy_order;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Integer getMin_volume() {
        return min_volume;
    }

    public void setMin_volume(Integer min_volume) {
        this.min_volume = min_volume;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Long getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Long system_id) {
        this.system_id = system_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public int getVolume_remain() {
        return volume_remain;
    }

    public void setVolume_remain(int volume_remain) {
        this.volume_remain = volume_remain;
    }

    public int getVolume_total() {
        return volume_total;
    }

    public void setVolume_total(int volume_total) {
        this.volume_total = volume_total;
    }
}
