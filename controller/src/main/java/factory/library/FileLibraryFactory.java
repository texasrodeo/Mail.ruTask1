package factory.library;

import com.google.inject.Inject;
import exceptions.LibraryNotEnoughSpaceException;
import factory.books.FileBookFactory;
import library.Library;
import model.Book;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class FileLibraryFactory implements LibraryFactory {

    @Inject
    private @NotNull
    FileBookFactory bookFactory;

    @Inject
    public FileLibraryFactory(@NotNull FileBookFactory bookFactory){
        this.bookFactory = bookFactory;
    }

    @Override
    public Library library(Integer capacity) throws LibraryNotEnoughSpaceException {
        List<Book> books = (List<Book>) bookFactory.books();
        return new Library(capacity, books);
    }


}
