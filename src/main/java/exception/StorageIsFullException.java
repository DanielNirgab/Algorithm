package exception;

public class StorageIsFullException extends RuntimeException {
    public StorageIsFullException() {
        super("Хранилище заполнено");
    }
}
