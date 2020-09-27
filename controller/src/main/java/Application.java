import com.google.gson.Gson;
import factory.LibraryFactory;
import library.Library;
import model.Book;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LibraryFactory libraryFactory = new LibraryFactory();
        Library library = libraryFactory.getLibrary();

        Gson gson = new Gson();
        library.getBooks(args[0], args[1]).forEach(b ->
            System.out.println(gson.toJson(b))
        );

    }
}
