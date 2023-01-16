package msv.epptec.enumeration;

import java.io.IOException;

/**
 * Co si vybral uzivatel
 */
public enum UserActionType {

    ADD_USER("1"), DEL_USER("2"), FIND_USER("3");

    /**
     * co je zadano na klavesnici, aby to byla tato akce
     */
    private final String userInput;

    UserActionType(String userInput) {
        this.userInput = userInput;
    }

    /**
     * podle vstupu uzivatele najde akci, ktera se ma provadet
     *
     * @param input vstup uzviatele
     * @return akce
     * @throws IOException kdyz se nenajde dana aktivita
     */
    public static UserActionType getUserAction(String input) throws IOException {
        for (UserActionType item : UserActionType.values())
            if (input != null && input.equals(item.userInput))
                return item;
        throw new IOException("Neznami vstup: " + input);
    }
}
