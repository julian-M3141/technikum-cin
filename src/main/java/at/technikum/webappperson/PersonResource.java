package at.technikum.webappperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//see http://localhost:8080/resources/persons


@RestController
@RequestMapping("/resources/persons")
public class PersonResource {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person retrieve(@PathVariable long id){
        return personRepository.findById(id).get();
    }

    @GetMapping
    public List<Person> retrieveAll(@RequestParam(defaultValue = "false") boolean all){
        if(all) {
            return personRepository.findAll();
        }
        return personRepository.findAllByActiveTrue();
    }

}
