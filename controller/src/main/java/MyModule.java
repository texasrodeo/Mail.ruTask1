import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import factory.books.BookFactory;
import factory.books.FileBookFactory;
import factory.library.FileLibraryFactory;
import factory.library.LibraryFactory;

public class MyModule extends AbstractModule {
    private final String path;

    public MyModule(String path){
        this.path = path;
    }

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
