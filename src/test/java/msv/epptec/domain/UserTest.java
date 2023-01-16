package msv.epptec.domain;

import msv.epptec.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void testSetName() throws ValidationException {
        var user = new User();
        assertThrows(ValidationException.class, () -> user.setName(""));
        user.setName("jmenoA");
        assertEquals("jmenoA", user.getName());
    }

    @Test
    void testSetSurname() throws ValidationException {
        var user = new User();
        assertThrows(ValidationException.class, () -> user.setSurname(""));
        user.setSurname("jmenoB");
        assertEquals("jmenoB", user.getSurname());
    }

    @Test
    void testSetBirthNumber() throws ValidationException {
        var user = new User();
        assertThrows(ValidationException.class, () -> user.setBirthNumber("jmenoUDY1"));
        assertThrows(ValidationException.class, () -> user.setBirthNumber("YYMMDDXXXX"));
        assertThrows(ValidationException.class, () -> user.setBirthNumber("2215301020"));
        user.setBirthNumber("2203301020");
        assertEquals("2203301020", user.getBirthNumber());
        user.setBirthNumber("220330/1020");
        assertEquals("2203301020", user.getBirthNumber());
        user.setBirthNumber("896205/1020");   //zenske
        assertEquals("8962051020", user.getBirthNumber());
    }

    @Test
    void testAge() throws ValidationException {
        User user = new User();
        user.setBirthNumber("2005071122");
        assertEquals(2, user.countAge());
        user.setBirthNumber("8362051122");   //zenske
        assertEquals(39, user.countAge());
        user.setBirthNumber("8105071122");
        assertEquals(41, user.countAge());
    }

}
