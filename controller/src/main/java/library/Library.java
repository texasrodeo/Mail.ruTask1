package library;

import com.google.gson.Gson;
import exceptions.EmptyCellException;
import exceptions.LibraryNotEnoughSpaceException;
import model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Library {

    private List<Book> books;

    public Library(Integer capacity, List<Book> books) throws LibraryNotEnoughSpaceException {
        this.books = Arrays.asList(new Book[capacity]);
        if(this.books.size()<books.size()){
            throw new LibraryNotEnoughSpaceException("Not enough cells");
        }
        else {
            for(int i=0;i<books.size();i++){
                this.books.set(i, books.get(i));
            }
        }
    }

    public String getBook(Integer cellNumber) throws EmptyCellException {
        if(this.books.get(cellNumber)!=null){
            Book b = this.books.get(cellNumber);
            String res= bookToString(b);
            this.books.set(cellNumber, null);
            return res;
        }
        throw new EmptyCellException("Cell is empty");
    }

    public void addBook(Book book) throws LibraryNotEnoughSpaceException{
        boolean added =false;
        for(int i=0;i<this.books.size();i++){
            if(this.books.get(i)==null){
                this.books.set(i,book);
                added=true;
                break;
            }
        }
        if(!added){
            throw new LibraryNotEnoughSpaceException("No free cells in the library");
        }

    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        this.books.forEach(b->{
            res.append(bookToString(b));
        });
        return res.toString();
    }

    private String bookToString(Book b){
        Gson gson = new Gson();
        StringBuilder res = new StringBuilder();
        res.append("Cell number ");
        res.append(this.books.indexOf(b));
        res.append(": ");
        res.append(gson.toJson(b));
        res.append("\n");
        return res.toString();
    }

    public List<Book> getBooks(){
        return this.books;
    }

}
