package exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryNotEnoughSpaceException extends Exception {
    private String message;
}
