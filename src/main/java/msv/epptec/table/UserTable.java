package msv.epptec.table;

import msv.epptec.domain.User;
import msv.epptec.exception.UserExists;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Tabulka uzivatelu
 */
public class UserTable {
    /**
     * data v tabulce (klic je RC)
     */
    private final Map<String, User> data = new HashMap<>();

    /**
     * Pridani noveho uzviatel
     */
    public void add(User item) throws UserExists {
        Optional<User> inDb = findByBirthNumber(item.getBirthNumber());
        if (inDb.isEmpty()) {
            data.put(item.getBirthNumber(), item);
        } else {
            throw new UserExists(inDb.get());
        }
    }

    /**
     * Vyhleda zaznam podle uzivatele
     */
    public Optional<User> findByBirthNumber(String birthNumber) {
        if (birthNumber == null)
            return Optional.empty();
        User result = data.get(birthNumber);
        if (result == null)
            return Optional.empty();
        return Optional.of(result);
    }

    public void delete(User user) {
        data.remove(user.getBirthNumber());
    }
}
