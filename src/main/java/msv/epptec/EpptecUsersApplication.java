package msv.epptec;

import msv.epptec.action.UserInput;
import msv.epptec.enumeration.UserActionType;
import msv.epptec.exception.ActionException;
import msv.epptec.service.UserConsoleService;

import java.io.IOException;

/**
 * Main aplikace pro zadavani vstupu pres konzoli
 */
public class EpptecUsersApplication {

    private final UserInput input = new UserInput();
    private final UserConsoleService userAction = new UserConsoleService();

    public static void main(String[] args) {
        new EpptecUsersApplication().execute();
    }

    private void execute() {
        processOneUserAction();
    }

    /**
     * provede jendu kaci, kterou chce uzivatel
     */
    private void processOneUserAction() {
        do {
            var action = showMenu();
            try {
                executeAction(action);
                System.out.println("Akce provedena");
            } catch (ActionException e) {
                System.err.println(e.getMessage());
            }
        } while (!endApplication());
    }

    private void executeAction(UserActionType action) throws ActionException {
        switch (action) {
            case ADD_USER -> userAction.addNewUser();
            case DEL_USER -> userAction.delUser();
            case FIND_USER -> {
                StringBuilder info = userAction.findUser();
                System.out.println("Uživatel nalezen: \n" + info);
            }
        }
    }

    /**
     * Jestli ma aplikace skoncit
     *
     * @return TRUE, pokud ma
     */
    private boolean endApplication() {
        try {
            String str = input.getInput("Ukončit aplikaci[y/n]? ");
            return "y".equalsIgnoreCase(str);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Zobrazi menu a zjisti, co uzivatel chce
     *
     * @return co si uzivatel zvolil
     */
    private UserActionType showMenu() {
        try {
            String str = input.getInput("""
                    1. Přidat osobu
                    2. Odebrat osobu
                    3. Vyhledat osobu podle rodného čísla
                    Zadejte volbu>\s""");
            return UserActionType.getUserAction(str);
        } catch (IOException e) {
            System.err.println("Nevalidni vstup");
            return showMenu();
        }
    }

}