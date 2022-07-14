package exception;

public class NullItemException extends RuntimeException {
    public NullItemException() {
        super("Не добавлено! Элемент null");
    }
}
