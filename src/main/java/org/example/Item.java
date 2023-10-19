package org.example;

import java.util.UUID;

public abstract class Item {
    private String title;
    private String uniqueID;
    private boolean isBorrowed;

    public Item(String title, String uniqueID) {
        this.title = title;
        this.uniqueID = generateUniqueID();
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public abstract void borrowItem();

    public abstract void returnItem();

    protected void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    private static int lastUsedID = 0;

    private String generateUniqueID() {

        String uuid = UUID.randomUUID().toString();

        String uniqueID = "ID" + uuid.substring(0, 8) + lastUsedID;
        lastUsedID++;
        return uniqueID;
    }
}
