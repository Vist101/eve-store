package com.pionw.eve_store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long eveId;
    private Long group_id;
    private Long icon_id;
    private Float packaged_volume;
    private Float volume;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEveId() {
        return eveId;
    }

    public void setEveId(Long eveId) {
        this.eveId = eveId;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(Long icon_id) {
        this.icon_id = icon_id;
    }

    public Float getPackaged_volume() {
        return packaged_volume;
    }

    public void setPackaged_volume(Float packaged_volume) {
        this.packaged_volume = packaged_volume;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
