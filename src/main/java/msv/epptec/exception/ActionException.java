package msv.epptec.exception;

/**
 * Chyba pri provadeni aplikace
 */
public class ActionException extends Exception {
    public ActionException(String msg) {
        super(msg);
    }
}
