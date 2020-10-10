import com.google.inject.*;
import exceptions.EmptyCellException;
import exceptions.LibraryNotEnoughSpaceException;
import factory.books.BookFactory;
import factory.books.FileBookFactory;
import factory.library.FileLibraryFactory;
import factory.library.LibraryFactory;
import library.Library;
import model.Author;
import model.Book;
import net.lamberto.junit.GuiceJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import static org.hamcrest.Matchers.*;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules(MainTest.TestModule.class)
public final class MainTest {

    private static String path = "C:\\Users\\Mvideo787\\IdeaProjects\\library\\books.txt";

    public static class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(BookFactory.class).to(FileBookFactory.class);
            bind(LibraryFactory.class).to(FileLibraryFactory.class);
        }


        @Provides
        public FileBookFactory provideFileBookFactory(){
            return new FileBookFactory(path);
        }
    }

    @Inject
    private LibraryFactory factory;

    @Test(expected= LibraryNotEnoughSpaceException.class)
    public void testNotEnoughCellsException() throws LibraryNotEnoughSpaceException {
        factory.library(10);
    }

    @Test
    public void testOrderOfCells() throws LibraryNotEnoughSpaceException {
        final Injector injector = Guice.createInjector(new TestModule());
        List<Book> books  = (ArrayList<Book>) injector.getInstance(FileBookFactory.class).books();
        Library library = new Library(100, books);
        for(int i=0;i<books.size();i++){
            assertThat(library.getBooks().get(i), is(books.get(i)));
        }
        for(int i=books.size();i<library.getBooks().size();i++){
            assertThat(library.getBooks().get(i), is(nullValue()));
        }

    }

    @Test
    public void checkBookTaking() throws LibraryNotEnoughSpaceException, EmptyCellException {
        Library library = factory.library(110);
        assertThat(library.getBook(10), is("Cell number 10: {\"name\":\"Book 0\",\"author\":{\"name\":\"Author1\"}}\n"));

    }

    @Test(expected = EmptyCellException.class)
    public void testEmptyCellException() throws EmptyCellException, LibraryNotEnoughSpaceException {
        Library library = factory.library(200);
        library.getBook(199);
    }

    @Test
    public void addNewBookTest() throws LibraryNotEnoughSpaceException, EmptyCellException {
        Library library = factory.library(110);
        library.getBook(10);
        assertThat(library.getBooks().get(10), is(nullValue()));
        library.addBook(new Book("Test Book", new Author("Test author")));
        assertThat(library.getBooks().get(10), is(notNullValue()));
    }

    @Test(expected = LibraryNotEnoughSpaceException.class)
    public void addNewBookToFullLibraryTest() throws LibraryNotEnoughSpaceException{
        Library library = factory.library(100);
        library.addBook(new Book("Test Book", new Author("Test author")));
    }

    @Test
    public void testLibraryToString() throws LibraryNotEnoughSpaceException {
        Library library = factory.library(100);
        assertThat(library.toString(), instanceOf(String.class));
    }

}
