package msv.epptec.action;

import msv.epptec.domain.User;
import msv.epptec.exception.ActionException;
import msv.epptec.exception.UserExists;
import msv.epptec.exception.ValidationException;
import msv.epptec.table.UserTable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementace prace s uzivatelem
 */
@Component
public class UserActionImpl implements UserAction {

    private final UserTable table = new UserTable();

    @Override
    public void addNewUser(User user) throws ActionException {
        try {
            table.add(user);
        } catch (UserExists e) {
            throw new ActionException("Daný uživatel již existuje");
        }
    }

    @Override
    public void delUser(String rc) throws ActionException {
        Optional<User> userOpt = table.findByBirthNumber(normalizeBirthNumber(rc));
        if (userOpt.isEmpty())
            throw new ActionException("Uživatel nebyl nalezen");
        table.delete(userOpt.get());
    }

    @Override
    public StringBuilder findUser(String rc) throws ActionException {
        try {
            Optional<User> userOpt = table.findByBirthNumber(normalizeBirthNumber(rc));
            if (userOpt.isEmpty())
                throw new ActionException("Uživatel nebyl nalezen");
            User user = userOpt.get();
            StringBuilder sb = new StringBuilder();
            sb.append("Jméno: ").append(user.getName()).append("\n");
            sb.append("Příjmení: ").append(user.getSurname()).append("\n");
            sb.append("RČ: ").append(user.getBirthNumber()).append("\n");
            sb.append("Věk: ").append(user.countAge()).append("\n");
            return sb;
        } catch (ValidationException e) {
            //nemelo by nastat, ale pro jistotu
            e.printStackTrace();  //do logu
            throw new ActionException("Internal error " + e.getMessage());
        }
    }

    /**
     * prevede rodne cislo na jednotny format pro vyhledavani
     */
    private String normalizeBirthNumber(String rc) {
        return rc.replaceAll("/", "");
    }

}
