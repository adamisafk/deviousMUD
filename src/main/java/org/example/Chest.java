package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Chest")
public class Chest {
    @Id
    @Column(name = "chest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "chest_name")
    private String name;
    @Column(name = "chest_description")
    private String description;
    @Column(name = "chest_item")
    private Integer itemCarried;

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

    public Integer getItemCarried() {
        return itemCarried;
    }
    public void setItemCarried(Integer itemCarried) {
        this.itemCarried = itemCarried;
    }

    public Chest() {
    }

    public void describeItems() {
        System.out.println("You find a ");
        System.out.println(items.getName() & ". ");
        System.out.println(items.getDescription);
    }

}


