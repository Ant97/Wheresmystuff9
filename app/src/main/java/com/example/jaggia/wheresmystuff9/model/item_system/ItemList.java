package com.example.jaggia.wheresmystuff9.model.item_system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jaggia on 6/21/17.
 */

public class ItemList {
    private LinkedList<Item> itemList;

    public ItemList(){
        itemList = new LinkedList<>();
    }

    /**
     * Get the itemObjectList
     *
     * @return return the itemObjectList
     */
    public LinkedList<Item> getItemObjectList(){return itemList; }
    /**
     * Add the itemObject to the linkedList itemObjectList
     *
     * @param item
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * Remove the itemObject from the linkedList itemObjectList
     *
     * @param item
     */
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    /**
     * Search for the item from the linkedList itemObjectList
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

    /**
     *
     * @return returns a string list of the list of items
     */
    public List listItems(){
        List<String> list = new ArrayList<>();
        for (Item i: itemList) {
            list.add(i.toString());
        }
        return list;
    }

    public void clearItems(){
        itemList.clear();
    }

}

