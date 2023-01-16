package msv.epptec.table;

import msv.epptec.domain.User;
import msv.epptec.exception.UserExists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Tabulka uzivatelu
 */
public class UserTable {
    /**
     * data v tabulce
     */
    private final List<User> data = new ArrayList<>();

    /**
     * Pridani noveho uzviatel
     */
    public void add(User item) throws UserExists {
        Optional<User> inDb = findByBirthNumber(item.getBirthNumber());
        if (inDb.isEmpty()) {
            data.add(item);
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
        return data.stream().filter(t -> birthNumber.equals(t.getBirthNumber())).findFirst();
    }

    public void delete(User user) {
        data.remove(user);
    }
}
