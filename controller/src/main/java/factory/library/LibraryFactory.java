package factory.library;

import exceptions.LibraryNotEnoughSpaceException;
import library.Library;

public interface LibraryFactory {
    Library library(Integer capacity) throws LibraryNotEnoughSpaceException;
}
