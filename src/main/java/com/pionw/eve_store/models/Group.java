package com.pionw.eve_store.models;

import jakarta.persistence.*;

@Entity
@Table(name = "group_eve")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long group_id;
    private Long category_id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String types;
    private Boolean published;

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long groupId) {
        this.group_id = groupId;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long categoryId) {
        this.category_id = categoryId;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
