package com.example.jaggia.wheresmystuff9.Model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by jaggia on 6/21/17.
 */

public class ItemList {
    LinkedList<Item> itemList = new LinkedList<>();

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public Item searchList(String itemName) {
        Iterator itr = itemList.iterator();
        while (itr.hasNext()) {
            Item item = (Item) itr.next();
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

}

