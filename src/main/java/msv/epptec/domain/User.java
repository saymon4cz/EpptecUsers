package msv.epptec.domain;

import msv.epptec.exception.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Ulozeny uzivatel
 */
public class User {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

    private String name;
    private String surname;
    /**
     * Rodne cislo
     */
    private String birthNumber;
    /**
     * do jakeho roku mam brat 1900
     */
    private static final int RC_LIMIT = 54;

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (isBlank(name))
            throw new ValidationException("Pole nesmi byt prazdne");
        this.name = name;
    }

    /**
     * standardne pouzit Apache, ale nemaji se pouzivate extyerni knihovny
     */
    private boolean isBlank(String name) {
        return name == null || "".equals(name);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ValidationException {
        if (isBlank(surname))
            throw new ValidationException("Pole nesmi byt prazdne");
        this.surname = surname;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) throws ValidationException {
        if (isBlank(birthNumber))
            throw new ValidationException("Pole nesmi byt prazdne");
        //YYMMDDXXXX nebo YYMMDD/XXXX
        if (!birthNumber.matches("\\d{6}/?\\d{4}"))
            throw new ValidationException("Nevalidni vstup: " + birthNumber);
        getBirtNumberDate(birthNumber);
        this.birthNumber = birthNumber.replaceAll("/", "");   //ukladat budu bez lomitka
    }

    private static LocalDate getBirtNumberDate(String birthNumber) throws ValidationException {
        try {
            int month = Integer.parseInt(birthNumber.substring(2, 4));
            if (month >= 50)   //zeny
                month -= 50;
            String monthStr = (month < 10 ? "0" : "") + month;
            int year = Integer.parseInt(birthNumber.substring(0, 2));
            String dateStr = birthNumber.substring(0, 2) + monthStr + birthNumber.substring(4, 6);
            LocalDate date = LocalDate.parse(dateStr, formatter);   //overim, ze to je validni
            if (year >= RC_LIMIT)
                date = date.minusYears(100);
            return date;
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new ValidationException("Nevalidni format RC (datum neni platne)");
        }
    }

    /**
     * vypocita, kolik je dane osobe let
     */
    public int countAge() throws ValidationException {
        LocalDate birtNumberDate = getBirtNumberDate(birthNumber);
        return (int) ChronoUnit.YEARS.between(birtNumberDate, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthNumber='" + birthNumber + '\'' +
                '}';
    }
}
