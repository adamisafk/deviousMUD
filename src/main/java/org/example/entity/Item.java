package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_description")
    private String description;
    @Column(name = "item_melee_damage")
    private Integer meleeDamage;
    @Column(name = "item_magic_damage")
    private Integer magicDamage;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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
