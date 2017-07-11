package com.example.jaggia.wheresmystuff9.model.item_system;

import com.example.jaggia.wheresmystuff9.model.user_system.User;

import java.util.Date;

/**
 * Created by James on 7/11/2017.
 */

public interface Item {

    String toString();

    User getUser();
    void setUser(User u);

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);

    Date getDate();
    void setDate(Date date);

    MyLocation getLocation();
    void setLocation(MyLocation location);

    ItemStatus getStatus();
    void setStatus(ItemStatus itemStatus);

    ItemType getType();
    void setType(ItemType itemType);

    ItemCategory getCategory();
    void setCategory(ItemCategory itemCategory);

    public abstract class ItemBuilder<T extends ItemBuilder<T>>{
        private final String name;
        private final MyLocation location;
        private final ItemType type;

        public ItemBuilder(){
            name = "default name";
            location = new MyLocation();
            type = ItemType.ILLEGAL;
        }
        public ItemBuilder(String name, MyLocation location, ItemType type){
            this.name = name;
            this.location = location;
            this.type = type;
        }
    }

}

