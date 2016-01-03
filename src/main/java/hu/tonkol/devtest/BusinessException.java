package hu.tonkol.devtest;

/**
 * Created by tonkol on 2016.01.03..
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
