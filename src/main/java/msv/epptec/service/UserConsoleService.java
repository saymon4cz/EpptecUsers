package msv.epptec.service;

import msv.epptec.action.UserAction;
import msv.epptec.action.UserActionImpl;
import msv.epptec.action.UserInput;
import msv.epptec.domain.User;
import msv.epptec.exception.ActionException;
import msv.epptec.exception.ValidationException;

import java.io.IOException;

/**
 * Pro zadavani vstupu uzivatelem pres konzoli
 */
public class UserConsoleService {

    private final UserInput input = new UserInput();

    private final UserAction userAction = new UserActionImpl();

    /**
     * nacteni vstupu z klavesnice a pridani noveho uzviatele
     */
    public void addNewUser() throws ActionException {
        try {
            User user = new User();
            user.setName(input.getInput("Jméno: "));
            user.setSurname(input.getInput("Příjmení: "));
            user.setBirthNumber(input.getInput("RČ: "));
            userAction.addNewUser(user);
        } catch (IOException e) {
            throw new ActionException("Vstup nebyl validni. Zvolte prosim znovu");
        } catch (ActionException exception) {
            throw exception;
        } catch (ValidationException e) {
            throw new ActionException("Chyba vstupu: " + e.getMessage());
        }
    }

    /**
     * smazani uzviatele, ktery definuje uzivatel na klavesnici
     */
    public void delUser() throws ActionException {
        try {
            String rc = input.getInput("RČ: ");
            userAction.delUser(rc);
        } catch (IOException e) {
            throw new ActionException("Vstup nebyl validni. Zvolte prosim znovu");
        }
    }

    /**
     * nalezeni uzivatele, ktery na klavesnici zada RC
     * @return informace pro zobrazeni
     */
    public StringBuilder findUser() throws ActionException {
        try {
            String rc = input.getInput("RČ: ");
            return userAction.findUser(rc);
        } catch (IOException e) {
            throw new ActionException("Vstup nebyl validni. Zvolte prosim znovu");
        }
    }

}
