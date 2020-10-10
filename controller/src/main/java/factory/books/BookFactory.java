package factory.books;

import library.Library;
import model.Author;
import model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface BookFactory {
     Collection<Book> books();
}
