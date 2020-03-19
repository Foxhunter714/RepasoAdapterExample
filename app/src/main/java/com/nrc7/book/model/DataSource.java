package com.nrc7.book.model;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public DataSource() {
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Book book = new Book();
            book.setName("Libro " + i);
            book.setAuthor("Autor " + i);
            books.add(book);
        }
        return books;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Divine Comedy","Dante Alighieri"));
        books.add(new Book("The Epic Of Gilgamesh","Unknown"));
        books.add(new Book("One Thousand and One Nights","Unknown"));
        books.add(new Book("Don Quijote De La Mancha","Miguel de Cervantes"));
        books.add(new Book("Crime and Punishment","Fyodor Dostoevsky"));
        books.add(new Book("Invisible Man","Ralph Ellison"));
        books.add(new Book("The Brothers Karamazov","Fyodor Dostoevsky"));
        books.add(new Book("The Old Man and the Sea","Ernest Hemingway"));
        return books;
    }

}
