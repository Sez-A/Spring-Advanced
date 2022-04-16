package bg.softuni.schedules.config;

import bg.softuni.schedules.repository.PersonRepository;
import bg.softuni.schedules.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer {
    private final PersonRepository personRepository;

    public DBInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void initializeDB() {
        Person pesho = new Person().setName("Pesho");
        Person ivan = new Person().setName("Ivan");

        this.personRepository.save(pesho);
        this.personRepository.save(ivan);

    }
}
