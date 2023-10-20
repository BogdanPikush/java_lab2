import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();

        Book book1 = new Book("Java", "15", "Evans");
        DVD dvd1 = new DVD("DVD", "23", 120);

        library.add(book1);
        library.add(dvd1);

        Patron patron1 = new Patron("Alex");
        library.registerPatron(patron1);
    }

    @Test
    public void testAddAndRemoveItem() {
        Book book = new Book("Python", "72", "Mattes");
        library.add(book);
        assertTrue(library.getItems().contains(book));
        library.remove(book);
        assertFalse(library.getItems().contains(book));
    }

    @Test
    public void testRegisterPatron() {
        Patron patron = new Patron("Alex");
        library.registerPatron(patron);
        assertTrue(library.getPatrons().contains(patron));
    }

    @Test
    public void testLendAndReturnItem() {
        Patron patron = new Patron("Alex");
        library.registerPatron(patron);

        Book book = new Book("JavaScript", "27", "averbeke");
        library.add(book);

        library.lendItem(patron, book);
        assertTrue(book.isBorrowed());
        assertTrue(patron.getBorrowedItems().contains(book));

        library.returnItem(patron, book);
        assertFalse(book.isBorrowed());
        assertFalse(patron.getBorrowedItems().contains(book));
    }

    @Test
    public void testListAvailableItems() {
        Book book = new Book("JavaScript", "38", "averbeke");
        DVD dvd = new DVD("DVD2", "95", 55);
        library.add(book);
        library.add(dvd);

        List<Item> availableItems = library.listAvailable();
        assertTrue(availableItems.contains(book));
        assertTrue(availableItems.contains(dvd));
    }

    @Test
    public void testListBorrowedItems() {
        Patron patron = new Patron("Alex");
        library.registerPatron(patron);

        Book book = new Book("C#", "56", "Brenson");
        DVD dvd = new DVD("DVD3", "64", 32);
        library.add(book);
        library.add(dvd);

        library.lendItem(patron, book);
        library.lendItem(patron, dvd);

        List<Item> borrowedItems = library.listBorrowed();
        assertTrue(borrowedItems.contains(book));
        assertTrue(borrowedItems.contains(dvd));
    }
}
