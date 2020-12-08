package org.example;

public class NPC {
    private String name;
    private String description;
    private Integer healthValue;
    private Integer armourValue;
    private Integer goldCarried;
    private Item itemCarried;
    private Boolean isFriendly;

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

    public Integer getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(Integer healthValue) {
        this.healthValue = healthValue;
    }

    public Integer getArmourValue() {
        return armourValue;
    }

    public void setArmourValue(Integer armourValue) {
        this.armourValue = armourValue;
    }

    public Integer getGoldCarried() {
        return goldCarried;
    }

    public void setGoldCarried(Integer goldCarried) {
        this.goldCarried = goldCarried;
    }

    public Item getItemCarried() {
        return itemCarried;
    }

    public void setItemCarried(Item itemCarried) {
        this.itemCarried = itemCarried;
    }

    public Boolean getFriendly() {
        return isFriendly;
    }

    public void setFriendly(Boolean friendly) {
        isFriendly = friendly;
    }

    public NPC() {
    }
}
