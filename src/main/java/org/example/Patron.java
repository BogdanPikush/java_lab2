package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patron {
    private String name;
    private String ID;
    private List<Item> borrowedItems;

    public Patron(String name) {
        this.name = name;
        this.ID = generateUniqueID();
        borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrow(Item item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        }
    }

    public void returnItem(Item item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        }
    }

    private static int lastUsedID = 0;

    private String generateUniqueID() {

        String uuid = UUID.randomUUID().toString();

        String uniqueID = "ID" + uuid.substring(0, 8) + lastUsedID;
        lastUsedID++;
        return uniqueID;
    }
}
