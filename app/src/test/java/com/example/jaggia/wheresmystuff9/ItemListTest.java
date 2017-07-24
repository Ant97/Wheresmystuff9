package com.example.jaggia.wheresmystuff9;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemList;
import com.example.jaggia.wheresmystuff9.model.item_system.Item;
import com.example.jaggia.wheresmystuff9.model.item_system.FoundItem;
import com.example.jaggia.wheresmystuff9.model.item_system.LostItem;
import com.example.jaggia.wheresmystuff9.model.item_system.MyLocation;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemStatus;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemCategory;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.List;
import java.util.LinkedList;
import java.util.Date;
import android.location.Location;

public class ItemListTest {
    private ItemList itemList;
    private List<Item> list;


    //-----------------------------helper methods--------------------------
    /**
     * This is a private method to check if 2 items are equal
     * @param expect the expected item
     * @param actual the actual item
     */
    private void assertItemEqual(Item expect, Item actual) {
        assertEquals("returned incorrect item", expect.getName(), actual.getName());
        assertEquals("returned incorrect item", expect.getUsername(), actual.getUsername());
        assertEquals("returned incorrect item", expect.getDate(), actual.getDate());
        assertEquals("returned incorrect item", expect.getDescription(), actual.getDescription());
        assertEquals("returned incorrect item", expect.getLocation(), actual.getLocation());
        assertEquals("returned incorrect item", expect.getType(), actual.getType());
        assertEquals("returned incorrect item", expect.getCategory(), actual.getCategory());
        assertSame("returned incorrect item", expect, actual);
    }

    /**
     * This is a private method that populate a list of items
     */
    private void populateItemList() {
        itemList = new ItemList();
        list = new LinkedList<>();
        Date date1 = new Date(2016, 7, 5);
        Date date2 = new Date(2017, 7, 1);
        Date date3 = new Date(2016, 12, 15);
        Date date4 = new Date(2017, 1, 23);
        Date date5 = new Date(2017, 4, 23);
        MyLocation loc1 = new MyLocation(new Location("Atlanta"));
        MyLocation loc2 = new MyLocation(new Location("Orlando"));
        MyLocation loc3 = new MyLocation(new Location("New York City"));
        MyLocation loc4 = new MyLocation(new Location("Chicago"));
        MyLocation loc5 = new MyLocation(new Location("Miami"));
        ItemStatus sr = ItemStatus.RESOLVED;
        ItemStatus su = ItemStatus.UNRESOLVED;
        ItemType tl = ItemType.FOUND;
        ItemType tf = ItemType.LOST;
        ItemCategory c1 = ItemCategory.ACCESSORY;
        ItemCategory c2 = ItemCategory.ELECTRONICS;
        ItemCategory c3 = ItemCategory.MISC;
        ItemCategory c4 = ItemCategory.PERSONAL;

        LostItem itemL1 = new LostItem("yuli1", "itemL1", "an item1", date1, loc1, "hug", sr, tl, c1);
        LostItem itemL2 = new LostItem("yuli2", "itemL2", "an item2", date2, loc2, "hug", su, tl, c2);
        LostItem itemL3 = new LostItem("yuli3", "itemL3", "an item3", date3, loc3, "hug", sr, tl, c4);
        LostItem itemL4 = new LostItem("yuli4", "itemL4", "an item4", date4, loc4, "hug", su, tl, c3);
        LostItem itemL5 = new LostItem("yuli5", "itemL5", "an item5", date5, loc5, "hug", sr, tl, c1);
        list.add(itemL1);
        list.add(itemL2);
        list.add(itemL3);
        list.add(itemL4);
        list.add(itemL5);

        FoundItem itemF1 = new FoundItem("an1", "itemF1", "an item1", date1, loc1, sr, tf, c1);
        FoundItem itemF2 = new FoundItem("an2", "itemF2", "an item2", date2, loc2, su, tf, c2);
        FoundItem itemF3 = new FoundItem("an3", "itemF3", "an item3", date3, loc3, sr, tf, c3);
        FoundItem itemF4 = new FoundItem("an4", "itemF4", "an item4", date4, loc4, su, tf, c4);
        FoundItem itemF5 = new FoundItem("an5", "itemF5", "an item5", date5, loc5, sr, tf, c1);
        list.add(itemF1);
        list.add(itemF2);
        list.add(itemF3);
        list.add(itemF4);
        list.add(itemF5);
    }
    /**
     * this method sets ItemList's item list to the item list passed in parameter
     * @throws RuntimeException if fields are not set successfully
     * @param itemList ItemList to set
     * @param list the list of item used to set itemList's list
     */
    private void setItemList(ItemList itemList, List<Item> list) {
        try {
            Field field = ItemList.class.getDeclaredField("itemList");
            field.setAccessible(true);
            field.set(itemList, list);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    //-------------------------------searchList Tests-----------------------------
    @Test
    public void searchListNull() {
        itemList = new ItemList();
        list = new LinkedList<>();
        setItemList(itemList, list);
        assertNull(itemList.searchList(null));
    }

    @Test
    public void searchListEmpty() {
        itemList = new ItemList();
        list = new LinkedList<>();
        setItemList(itemList, list);
        assertNull(itemList.searchList("phone"));
    }

    @Test
    public void searchListThere() {
        populateItemList();
        setItemList(itemList, list);
        for (int i = 0; i < list.size(); i++) {
            assertItemEqual(list.get(i), itemList.searchList(list.get(i).getName()));
        }
    }

    @Test
    public void searchListNotThere() {
        Date date1 = new Date(2016, 7, 5);
        Date date2 = new Date(2017, 7, 1);

        MyLocation loc1 = new MyLocation(new Location("Atlanta"));
        MyLocation loc2 = new MyLocation(new Location("Orlando"));

        ItemStatus sr = ItemStatus.RESOLVED;
        ItemStatus su = ItemStatus.UNRESOLVED;
        ItemType tl = ItemType.FOUND;
        ItemType tf = ItemType.LOST;
        ItemCategory c1 = ItemCategory.ACCESSORY;
        ItemCategory c2 = ItemCategory.ELECTRONICS;
        FoundItem itemNF1 =
                new FoundItem("an4", "itemNF1", "an item", date1, loc1, su, tf, c1);
        FoundItem itemNF2 =
                new FoundItem("an5", "itemNF2", "an item", date2, loc2, sr, tf, c2);
        LostItem itemNL1 =
                new LostItem("yuli1", "itemNL1", "an item", date1, loc1, "hug", sr, tl, c1);
        LostItem itemNL2 =
                new LostItem("yuli2", "itemNL2", "an item", date2, loc2, "hug", su, tl, c2);
        populateItemList();
        setItemList(itemList,list);
        assertNull("should return null when item is not there",
                itemList.searchList(itemNF1.getName()));
        assertNull("should return null when item is not there",
                itemList.searchList(itemNF2.getName()));
        assertNull("should return null when item is not there",
                itemList.searchList(itemNL1.getName()));
        assertNull("should return null when item is not there",
                itemList.searchList(itemNL2.getName()));


    }

}
