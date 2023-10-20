package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library implements IManageable {
    private List<Item> items;
    private List<Patron> patrons;

    public Library() {
        items = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public void remove(Item item) {
        items.remove(item);
    }

    @Override
    public List<Item> listAvailable() {
        List<Item> availableItems = new ArrayList<>();
        for (Item item : items) {
            if (!item.isBorrowed()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    @Override
    public List<Item> listBorrowed() {
        List<Item> borrowedItems = new ArrayList<>();
        for (Patron patron : patrons) {
            borrowedItems.addAll(patron.getBorrowedItems());
        }
        return borrowedItems;
    }

    public void registerPatron(Patron patron) {
        patrons.add(patron);
    }

    public void lendItem(Patron patron, Item item) {
        if (!patrons.contains(patron)) {
            System.out.println("Patron is not registered in the library.");
            return;
        }

        if (!items.contains(item)) {
            System.out.println("Item is not available in the library.");
            return;
        }

        if (!item.isBorrowed()) {
            patron.borrow(item);
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    public void returnItem(Patron patron, Item item) {
        if (!patrons.contains(patron)) {
            System.out.println("Patron is not registered in the library.");
            return;
        }

        if (!items.contains(item)) {
            System.out.println("Item is not available in the library.");
            return;
        }

        if (item.isBorrowed() && patron.getBorrowedItems().contains(item)) {
            patron.returnItem(item);
        } else {
            System.out.println("Item is not borrowed by the patron.");
        }
    }
}
