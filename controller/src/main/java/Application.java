import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import exceptions.LibraryNotEnoughSpaceException;
import factory.books.BookFactory;
import factory.library.LibraryFactory;
import library.Library;

public class Application {
    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new MyModule(args[0]));
        try {
            Library library = injector.getInstance(LibraryFactory.class).library(Integer.parseInt(args[1]));
            System.out.println(library);
        }
        catch (LibraryNotEnoughSpaceException ex){
            System.out.println(ex.getMessage());
        }

    }
}
