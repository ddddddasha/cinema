package controller;

import model.Film;
import service.FilmService;
import service.FilmServiceImpl;
import service.TicketService;
import service.TicketServiceImpl;
import util.IncorrectInput;
import util.dateTime.DateTimeValidator;
import util.dateTime.DateTimeValidatorImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import static util.constants.Constants.*;

public class EventController {
    private final FilmService filmService = new FilmServiceImpl();
    private final TicketService ticketService = new TicketServiceImpl();
    private final DateTimeValidator dateTimeValidator = new DateTimeValidatorImpl();
    private final IncorrectInput input = new IncorrectInput();
    private final Scanner scanner = new Scanner(System.in);

    public void adminFilmMenu()  {
        System.out.println(System.lineSeparator() + ADMIN_FILM_MENU);
        Scanner scanner = new Scanner(System.in);
        var step = scanner.nextLine();
        switch (step){
            case ONE:
                filmService.create();
                break;
            case TWO:
                System.out.println(filmService.getAllFilms());
                break;
            case THREE:
                System.out.println(ENTER_FILM_ID_TO_DELETE);
                Long filmId = scanner.nextLong();
                ticketService.deleteTicketsByFilmId(filmId);
                filmService.deleteFilmById(filmId);
                break;
            case FOUR:
                System.out.println(filmService.getAllFilms());
                break;
            case FIVE:
                System.out.println(filmService.getAllFilms());
                System.out.println(ENTER_FILM_ID_TO_UPDATE);
                Long fId = input.integerInput();
                updateFilmMenu(filmService.getByFilmId(fId));
                break;
            case ZERO:
                break;
            default:
                System.out.println(INCORRECT_INPUT);
        }
    }

    private void updateFilmMenu(Film film){
        while (true){
            System.out.println(UPDATE_FILM_MENU);
            var step = scanner.nextLine();
            switch (step){
                case ONE:
                    System.out.println(ENTER_FILM_NAME);
                    filmService.updateFilmName(film, scanner.nextLine());
                    break;
                case TWO:
                    System.out.println(ENTER_FILM_DATE);
                    String newDate = scanner.nextLine();
                    LocalDate ld = dateTimeValidator.convertStrToLocalDate(newDate);//может зациклить пока правильно не введет
                    filmService.updateDate(film, ld);
                    break;
                case THREE :
                    System.out.println(ENTER_FILM_TIME);
                    LocalTime newTime = dateTimeValidator.convertStrToLocalTime(scanner.nextLine());
                    filmService.updateTime(film, newTime);
                    break;
                case FOUR:
                    System.out.println(ENTER_FILM_COST);
                    filmService.updateCost(film, input.doubleInput());
                    break;
                case ZERO:
                    adminFilmMenu();
                    break;
                default:
                    System.out.println(INCORRECT_INPUT);
            }
        }
    }

    public void ticketMenu(Long id){
        System.out.println(TICKET_MENU);
        var step = scanner.nextLine();
        switch (step){
            case  ONE:
                System.out.println(ENTER_FILM_ID);
                ticketService.getFreeTickets(scanner.nextLong());
                break;
            case TWO:
                System.out.println(filmService.getAllFilms());
                ticketService.buyTicket(id);
                break;
            case THREE:
                System.out.println(ENTER_TICKET_ID_TO_DELETE);
                System.out.println(ticketService.getTicketsForUser(id));
                Long ticketId = input.integerInput();
                ticketService.deleteTicketById(ticketId, id);
                break;
            case FOUR:
                System.out.println(ENTER_USER_ID_TO_BUY_FOR_HIM);
                Long userId = input.integerInput();
                System.out.println(filmService.getAllFilms());
                ticketService.buyTicket(userId);
                break;
            case FIVE:
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
}
