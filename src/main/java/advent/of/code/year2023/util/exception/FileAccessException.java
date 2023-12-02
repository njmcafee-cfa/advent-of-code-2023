package advent.of.code.year2023.util.exception;

public class FileAccessException extends RuntimeException {
        public FileAccessException(String message, Throwable cause) {
            super(message, cause);
        }
}
