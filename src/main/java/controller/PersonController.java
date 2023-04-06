package controller;

import model.Person;
import service.*;
import util.IncorrectInput;
import util.constants.Role;

import java.util.Scanner;
import static util.constants.Role.*;
import static util.constants.Constants.*;

//контроллер с меню общается
//предназначен для обработки запросов пользователя и возврата ему данных
//его задача - это вызов нужных методов
//Он используется для обозначения класса как обработчика веб-запросов.
public class PersonController {
    private final PersonService personService = new PersonServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();
    private final TicketService ticketService = new TicketServiceImpl();
    private final EventController eventController = new EventController();
    private final IncorrectInput input = new IncorrectInput();
    private final Scanner scanner = new Scanner(System.in);
    public void startMenu(){
        while (true){
            System.out.println(System.lineSeparator() + START_MENU);
            Scanner scanner = new Scanner(System.in);
            var step = scanner.nextLine();
            switch (step) {
                case ONE -> signIn();
                case TWO -> logIn();
                case ZERO -> System.exit(0);
                default -> System.out.println(INCORRECT_INPUT + System.lineSeparator());
            }
        }
    }

    public void signIn() {
        int count = 0;
        System.out.println(System.lineSeparator() + REGISTRATION_MENU);
        System.out.println(ENTER_LOGIN);
        String username = scanner.nextLine();
        if (personService.getByUsername(username) == null) {
            System.out.println(ENTER_PASSWORD);
            String password = scanner.nextLine();
            while (true) {
                System.out.print(REPEAT_PASSWORD);
                String tempPass = scanner.next();
                if (password.equals(tempPass)) {
                    personService.create(username, password);
                    break;
                } else if (count < FOUR_ATTEMPTS) {
                    System.out.println(System.lineSeparator() + REPEAT_PASS_ERROR);
                    System.out.println(System.lineSeparator() + REPEAT);
                    count++;
                } else {
                    System.out.println(TOO_MANY_ATTEMPTS);
                    break;
                }
            }
        }
        else {
            System.out.println(USERNAME_EXISTS);

        }
    }

    public void logIn(){
        while (true){
            System.out.println(System.lineSeparator() + ENTRY_MENU);
            Person person = personService.logIn();
            if (person!=null) {
                String personRole = person.getRole();
                if(ADMIN.getTitle().equals(personRole)){
                    adminMainMenu(person.getId());
                } else if (MANAGER.getTitle().equals(personRole)) {
                    managerMenu(person.getId());
                } else if (USER.getTitle().equals(personRole)) {
                    userMenu(person.getId());
                }
                else {
                    System.out.println(INCORRECT_INPUT);
                }
            }
            else {
                System.out.println(LOG_IN_FAILED);
            }
        }
    }

    public void adminMainMenu(Long id){
        while (true){
            System.out.println(System.lineSeparator() + ADMIN_MAIN_MENU);
            Scanner scanner = new Scanner(System.in);
            var step = scanner.nextLine();
            switch (step) {
                case ONE -> adminPersonMenu();
                case TWO -> eventController.adminFilmMenu();
                case THREE -> eventController.ticketMenu(id);
                case ZERO -> startMenu();
                default -> System.out.println(INCORRECT_INPUT);
            }
        }
    }

    public void adminPersonMenu(){
        while (true){
            System.out.println(System.lineSeparator() + ADMIN_PERSON_MENU);
            Scanner scanner = new Scanner(System.in);
            var step = scanner.nextLine();
            switch (step){
                case ONE:
                    signIn();
                    break;
                case TWO:
                    System.out.println(ENTER_USER_ID_TO_CHANGE);
                    Long id = input.integerInput();
                    System.out.println(ENTER_NEW_LOGIN);
                    String username = scanner.next();
                    System.out.println(ENTER_NEW_PASSWORD);
                    String password = scanner.next();
                    personService.updatePersonById(id, new Person(id, username, password, Role.USER.getTitle()));
                    break;
                case THREE:
                    System.out.println(ENTER_USER_ID_TO_DELETE);
                    personService.deleteById(scanner.nextLong());
                    break;
                case FOUR:
                    personService.showAllPersons();
                    break;
                case ZERO:
                    break;
                default:
                    System.out.println(INCORRECT_INPUT + System.lineSeparator());
            }
        }
    }

    public void managerMenu(Long managerId)  {
        System.out.println(MANAGER_MAIN_MENU);
        var step = scanner.nextLine();
        switch (step){
            case ONE:
                System.out.println(filmService.getAllFilms());
                break;
            case TWO:
                System.out.println(ENTER_FILM_ID_TO_UPDATE);
                Long filmId = input.integerInput();
                filmService.updateFilmById(filmId, filmService.getByFilmId(filmId));
                break;
            case THREE:
                System.out.println(System.lineSeparator() + filmService.getAllFilms());
                ticketService.buyTicket(managerId);
                break;
            case FOUR:
                System.out.println(ticketService.getTicketsForUser(managerId));
                System.out.println(ENTER_TICKET_ID_TO_DELETE);
                Long ticketId = input.integerInput();
                ticketService.deleteTicketById(ticketId, managerId);
                break;
            case FIVE:
                System.out.println(ENTER_USER_ID_TO_BUY_FOR_HIM);
                Long userId = input.integerInput();
                System.out.println(filmService.getAllFilms());
                ticketService.buyTicket(userId);
                break;
            case SIX:
                System.out.println(ENTER_USER_ID_TO_DELETE_HIS_TICKET);
                Long userId1 = input.integerInput();
                System.out.println(ticketService.getTicketsForUser(userId1));
                System.out.println(ENTER_TICKET_ID_TO_DELETE);
                Long ticketId1 = input.integerInput();
                ticketService.deleteTicketById(ticketId1, userId1);
                break;
            case ZERO:
                break;
            default:
                System.out.println(INCORRECT_INPUT);
        }
    }

    public void userMenu(Long userId){
        while (true){
            System.out.println(System.lineSeparator() + USER_MENU);
            var step = scanner.next();
            switch (step) {
                case ONE -> System.out.println(filmService.getAllFilms());
                case TWO -> {
                    System.out.println(filmService.getAllFilms());
                    ticketService.buyTicket(userId);
                }
                case THREE -> {
                    System.out.println(ENTER_TICKET_ID_TO_DELETE);
                    System.out.println(ticketService.getTicketsForUser(userId));
                    Long id = scanner.nextLong();
                    ticketService.deleteTicketById(id, userId);
                }
                case FOUR -> ticketService.getTicketsForUser(userId);
                case FIVE -> {
                    System.out.println(ENTER_FILM_ID);
                    ticketService.getFreeTickets(input.integerInput());
                }
                case ZERO -> startMenu();
                default -> {
                    System.out.println(INCORRECT_INPUT);
                    userMenu(userId);
                }
            }
        }
    }
}
