package com.example.jaggia.wheresmystuff9.Model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by jaggia on 6/21/17.
 */

public class ItemList {
    private LinkedList<Item> itemList;

    public ItemList(){
        itemList = new LinkedList<>();
    }


    public LinkedList<Item> getItemList(){return itemList; }
    /**
     * Add the item to the linkedList itemList
     *
     * @param item
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * Remove the item from the linkedList itemList
     *
     * @param item
     */
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    /**
     * Search for the item from the linkedList itemList
     *
     * @param itemName the string name of the item
     * @return The item if found, null if not
     */
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

