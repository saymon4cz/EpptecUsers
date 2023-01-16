package msv.epptec.exception;

import msv.epptec.domain.User;

/**
 * Uzivatel pro praidani jiz existuje
 */
public class UserExists extends Exception {

    /**
     * kdyby chtel vedet, jake udaje ma ten uzivatel, co existuje
     */
    private final User user;

    public UserExists(User user) {
        this.user = user;
    }

}
