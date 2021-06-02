package at.technikum.webappperson;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//integration test

@SpringBootTest
@ActiveProfiles("test")
@Transactional  // rollbacks transaction after each method
@Tag("extended")
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave(){
        var person = new Person(Sex.MALE,"Maxi","Musterkind", LocalDate.of(2010,12,24),true);
        person = personRepository.save(person);

        assertNotNull(person.getId());
        assertEquals(3,personRepository.count());
    }

    @Test
    public void testFindAllByActiveTrue(){
        var persons = personRepository.findAllByActiveTrue();
        assertEquals(1,persons.size());
    }

    @Test
    public void testDelete(){
        personRepository.deleteById(1L);
        assertEquals(1,personRepository.count());
    }

}
