package msv.epptec.table;

import msv.epptec.domain.User;
import msv.epptec.exception.UserExists;
import msv.epptec.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTableTest {

    @Test
    void testAdd() throws UserExists, ValidationException {
        UserTable userTable = new UserTable();
        final var item = new User();
        item.setBirthNumber("8105071122");
        userTable.add(item);
        assertThrows(UserExists.class, () -> userTable.add(item));
        var item2 = new User();
        item2.setBirthNumber("810507/1122");
        assertThrows(UserExists.class, () -> userTable.add(item2));
    }

    @Test
    void testFindByBirthNumber() throws ValidationException, UserExists {
        UserTable userTable = new UserTable();
        var item = new User();
        item.setBirthNumber("8105071122");
        userTable.add(item);
        assertTrue(userTable.findByBirthNumber("test").isEmpty());
        assertTrue(userTable.findByBirthNumber("8105071122").isPresent());
    }

    @Test
    void testDelete()  throws ValidationException, UserExists {
        UserTable userTable = new UserTable();
        var item = new User();
        item.setBirthNumber("8105071122");
        userTable.add(item);
        userTable.delete(item);
        assertTrue(true);
    }

}
