package at.technikum.webappperson;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("!test")
public class DBInitializer {
    @Autowired
    private PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady(){
        personRepository.saveAll(
                List.of(
                        new Person(Sex.MALE,"Max","Mustermann", LocalDate.of(1970,1,1),true),
                        new Person(Sex.FEMALE,"Maxima","Musterfrau", LocalDate.of(1971,1,1),true)
                )
        );
    }


}
