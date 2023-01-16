package msv.epptec.service;

import msv.epptec.action.UserAction;
import msv.epptec.domain.User;
import msv.epptec.exception.ActionException;
import msv.epptec.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST operace pro uzivatele
 */
@RestController
public class RestEpptecService {

    @Autowired
    private UserAction userAction;

    /**
     * vraceni informaci o uzivateli dle zadaneho RC
     */
    @GetMapping("/getUser")
    public StringBuilder getUser(@RequestParam(value = "birthNumber") String rc) throws ActionException {
        return userAction.findUser(rc);
    }

    /**
     * pridani noveho uzivatele
     */
    @PostMapping("/addUser")
    public void addUser(@RequestParam(name = "name") String name, @RequestParam(name = "surname") String surname, @RequestParam(name = "birthNumber") String rc)
            throws ActionException, ValidationException {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setBirthNumber(rc);
        userAction.addNewUser(user);
    }

    /**
     * smazani uzivatele
     */
    @DeleteMapping("/delUser")
    public void delUser(@RequestParam(name = "birthNumber") String rc)
            throws ActionException {
        userAction.delUser(rc);
    }

}
