package library;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();

    public Library(){

    }

    public Library(List<Book> books){
        this.books = books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public List<Book> getBooks(String authorName, String authorSurname){
        List<Book> result = new ArrayList<>();
        books.forEach(b -> {
           if(b.getAuthor().getName().equals(authorName) && b.getAuthor().getSurname().equals(authorSurname)){
               result.add(b);
           }
        });
        return result;
    }

}
