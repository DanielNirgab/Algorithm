package exception;

public class WrongIndexException extends RuntimeException {
    public WrongIndexException () {
        super("Не верный индекс");
    }
}
