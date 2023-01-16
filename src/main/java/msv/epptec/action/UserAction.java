package msv.epptec.action;

import msv.epptec.domain.User;
import msv.epptec.exception.ActionException;

/**
 * Co vse mohu delat s uzivatelem
 */
public interface UserAction {

    /**
     * Ziska data pro noveho uzivatele a vlozi jej
     *
     * @param user jaky uzivatel
     */
    void addNewUser(User user) throws ActionException;

    /**
     * Zjisti, jakeho uzviatele ma smazat a provede to
     *
     * @param rc s jakym RC to ma smazat
     */
    void delUser(String rc) throws ActionException;

    /**
     * Pokusi se smazat daneho uzivatele
     *
     * @param rc pro jake RC to ma hledat
     * @return udaje s informacemi o danem uzivateli
     */
    StringBuilder findUser(String rc) throws ActionException;

}
