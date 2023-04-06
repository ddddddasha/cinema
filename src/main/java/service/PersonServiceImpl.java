package service;

import model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Logging;
import util.PasswordHashing;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static util.constants.Constants.*;
import static util.constants.Role.*;
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository = new PersonRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final PasswordHashing passwordHashing= new PasswordHashing();
    private final Logging logging = new Logging();

    @Override
    public boolean create(String username, String password){ //изменить без вложеннности такой большой
        System.setProperty("logFile", username);
        Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
        System.out.println(System.lineSeparator() + USER_REGISTERED + username + STATUS_REGISTERED);
        logger.info("User " + username + " has registered at " + new Date());
        return personRepository.create(new Person(username, passwordHashing.hashPassword(password), USER.getTitle()));
    }

    @Override
    public Person getByUsername(String username) {
        return personRepository.getByUsername(username);
    }

    @Override
    public Person getById(Long id) {
        return personRepository.getById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    public boolean deleteById(Long id) {
        return personRepository.deleteById(id);
    }

    @Override
    public Person logIn() {
        System.out.println(ENTER_LOGIN);
        String username = scanner.nextLine();
        Person person = personRepository.getByUsername(username);

        System.setProperty("logFile", username);
        Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
        //Logger logger = logging.start(username);
        if(person != null){
            System.setProperty("logFile", username);
            logger.info("User " + username + " logged in at " + new Date());
            System.out.println("Enter the password");//вводить пароль повторно Ўифрование ’еширование
            String password = scanner.nextLine();
            String passwordDb = person.getPassword();
            if (passwordHashing.verifyPassword(password, passwordDb)){
                System.out.println(System.lineSeparator() + USER_REGISTERED + username + STATUS_AUTHORIZED);
                //System.setProperty("username", "logFile");
                return personRepository.getByUsername(username);
            }
            else System.out.println(INCORRECT_PASSWORD);

        }
        else {
            System.out.println(INCORRECT_LOGIN);
            logger.warn("Incorrect login: " + username);
        }

        return null;
    }

    @Override
    public boolean updatePersonById(Long id, Person person) {
        personRepository.update(person, id);
        return true;
    }

    public void showAllPersons() {
        Stream<Person> stream = personRepository.getAllPersons().stream();
        stream
                .sorted(Comparator.comparing(Person::getUsername))
                .forEach(System.out::println);
    }
}
