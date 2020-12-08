package org.example;

public class Item {
    private String name;
    private String description;
    private Integer meleeDamage;
    private Integer magicDamage;

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

    public Integer getMeleeDamage() {
        return meleeDamage;
    }

    public void setMeleeDamage(Integer meleeDamage) {
        this.meleeDamage = meleeDamage;
    }

    public Integer getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(Integer magicDamage) {
        this.magicDamage = magicDamage;
    }

    public Item() {
    }
}
