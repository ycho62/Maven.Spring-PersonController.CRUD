package io.zipcoder.crudapp;

import io.zipcoder.crudapp.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    PersonRepository personRepository;


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }


    @GetMapping( "/{id}")
    public ResponseEntity<Person>getPerson(@PathVariable("id") Long id){
       Person person = personRepository.findById(id).get();
       if(person == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Person>> getPersonList(){
        List<Person> personList = (List<Person>) personRepository.findAll();
        if (personList == null || personList.isEmpty()){
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(HttpStatus.OK);

    }

    @PutMapping( "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id,String firstName, String lastName) {
    Person currentPerson = new Person(firstName, lastName, id) ;
    if (personRepository.existsById(id)){
        return new ResponseEntity<>(personRepository.save(currentPerson), HttpStatus.OK);
    }
        return new ResponseEntity<>(personRepository.save(currentPerson), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> DeletePerson(@PathVariable("id") Long id){
        Person p = personRepository.findById(id).get();
        personRepository.delete(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
