package org.example;

import javax.persistence.*;

public class Chest {
    private String name;
    private String description;
    private Item itemCarried;

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

    public Item getItemCarried() {
        return itemCarried;
    }

    public void setItemCarried(Item itemCarried) {
        this.itemCarried = itemCarried;
    }

    public Chest() {
    }
}


