package factory;

import library.Library;
import model.Author;
import model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryFactory {

    private static Library mySampleLibrary;

    private void generateSampleLibrary(){
        Author author1 = new Author("Ivan", "Ivanov");
        Author author2 = new Author("Petr","Petrov");
        Author author3 = new Author("Vasilii","Vasiliev");

        Book book1 = new Book("Book 1", author1);
        Book book2 = new Book("Book 2", author1);
        Book book3 = new Book("Book 3", author2);
        Book book4 = new Book("Book 4", author2);
        Book book5 = new Book("Book 5", author3);
        Book book6 = new Book("Book 6", author3);
        Book book7 = new Book("Book 7", author3);

        List<Book> books = Arrays.asList(book1, book2, book3, book4, book5, book6, book7);

        mySampleLibrary = new Library(books);

    }

    public LibraryFactory(){
        this.generateSampleLibrary();
    }

    public Library getLibrary(){
        return mySampleLibrary;
    }
}
