package service;

import model.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import util.dateTime.DateTimeValidator;
import util.dateTime.DateTimeValidatorImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import static util.constants.Constants.*;

public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository = new FilmRepositoryImpl();
    private final TicketService ticketService = new TicketServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeValidator dateTimeValidator = new DateTimeValidatorImpl();

    @Override
    public boolean create() {
        Film film = new Film();
        System.out.println(ENTER_FILM_NAME_TO_CREATE);
        String filmName = scanner.nextLine();
        film.setFilmName(filmName);
        while (true){
            System.out.println(ENTER_FILM_DATE_TO_CREATE);
            String filmDate = scanner.nextLine();
            LocalDate ld = dateTimeValidator.convertStrToLocalDate(filmDate);
            if(ld == null){
                System.out.println();
                System.out.println(INCORRECT_DATE_INPUT);
                System.out.println(REPEAT);
                System.out.println();
            }
            else {
                film.setFilmDate(ld);
                break;
            }
        }
        while (true){
            System.out.println(ENTER_FILM_TIME_TO_CREATE);
            String filmTime = scanner.nextLine();
            LocalTime lt = dateTimeValidator.convertStrToLocalTime(filmTime);
            if(lt == null){
                System.out.println(INCORRECT_INPUT);
                System.out.println(REPEAT);
            }
            else {
                film.setFilmTime(lt);
                break;
            }
        }
        System.out.println(ENTER_FILM_COST_TO_CREATE);
        Double cost = scanner.nextDouble();
        film.setCost(cost);
        filmRepository.create(film);
        ticketService.createTickets(film);
        return true;
    }


    @Override
    public Film getByFilmName(String filmName) {
        return filmRepository.getByFilmName(filmName);
    }

    @Override
    public Film getByFilmId(Long id) {
        return filmRepository.getById(id);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.getAllFilms();
    }

    @Override
    public boolean updateFilmById(Long id, Film film) {
        return filmRepository.update(film, id)!= null;
    }

    @Override
    public boolean deleteFilmById(Long id) {
        return filmRepository.deleteById(id);
    }

    @Override
    public boolean updateFilmName(Film film, String filmName) { //убрать фильм переменную
        return filmRepository.updateFilmName(film, filmName) != null; /*" film successfully edited" : "error";*/
    }

    @Override
    public boolean updateDate(Film film, LocalDate date) {
        return filmRepository.updateDate(film, date) != null;
    }

    @Override
    public boolean updateTime(Film film, LocalTime time) {
        return filmRepository.updateTime(film, time) != null;
    }

    @Override
    public boolean updateCost(Film film, Double cost) {
        return filmRepository.updateCost(film, cost) != null;
    }
}
