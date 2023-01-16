package msv.epptec.action;

import msv.epptec.domain.User;
import msv.epptec.exception.ActionException;
import msv.epptec.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserActionTest {

    private UserActionImpl userAction;

    @BeforeEach
    void before() {
        userAction = new UserActionImpl();
    }

    @Test
    void testAddNewUser() throws ValidationException, ActionException {
        var user = new User();
        user.setBirthNumber("8105071122");
        userAction.addNewUser(user);
        //dalsi pridani
        assertThrows(ActionException.class, () -> userAction.addNewUser(user));
    }

    @Test
    void testDelUser() throws ValidationException, ActionException {
        var user = new User();
        user.setBirthNumber("8105071122");
        userAction.addNewUser(user);
        assertThrows(ActionException.class, () -> userAction.delUser("147"));
        userAction.delUser("8105071122");
        assertThrows(ActionException.class, () -> userAction.delUser("8105071122"));
    }

    @Test
    void testFindUser() throws ValidationException, ActionException {
        var user = new User();
        user.setBirthNumber("8105071122");
        userAction.addNewUser(user);
        StringBuilder res = userAction.findUser("8105071122");
        assertNotNull(res);
        assertThrows(ActionException.class, () -> userAction.findUser("del"));
    }

}
