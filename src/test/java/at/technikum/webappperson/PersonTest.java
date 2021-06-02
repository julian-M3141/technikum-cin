package at.technikum.webappperson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp(){
        person = new Person(Sex.MALE,"Max","Mustermann", LocalDate.of(1970,1,1),true);
    }

    @Test
    public void testGetName(){
        //Person person = new Person(Sex.MALE,"Max","Mustermann", LocalDate.of(1970,1,1),true);
        assertEquals("Max Mustermann",person.getName());
    }

    @Test
    public void testGetNameWithFirstNameNull(){
        //Person person = new Person(Sex.MALE,"Max","Mustermann", LocalDate.of(1970,1,1),true);
        person.setFirstname(null);
        assertThrows(
                IllegalArgumentException.class,
                () -> person.getName()
        );
    }

    @Test
    public void testGetNameWithFirstNameBlank(){
        person.setFirstname("");
        assertThrows(
                IllegalArgumentException.class,
                person::getName
        );
    }

    @Test
    public void testGetNameWithLastNameNull(){
        person.setLastname(null);
        assertThrows(
                IllegalArgumentException.class,
                person::getName
        );
    }

    @Test
    public void testGetNameWithLastNameBlank(){
        person.setLastname("");
        assertThrows(
                IllegalArgumentException.class,
                person::getName
        );
    }

    //TODO add more tests here
}
