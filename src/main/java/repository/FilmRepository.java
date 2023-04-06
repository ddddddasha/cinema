package repository;

import model.Film;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FilmRepository {

    /**
     * Создание и сохранение фильма в базу данных.
     */
    boolean create(Film film);

    /**
     * Получение фильма по ID.
     */
    Film getById(Long id);

    /**
     * Получение фильма по названию.
     */
    Film getByFilmName(String username);

    /**
     * Получение всех фильмов.
     */
    List<Film> getAllFilms();

    /**
     * Изменение фильма по названию.
     */
    Film updateFilmName(Film film, String filmName);

    /**
     * Изменение даты фильма.
     */
    Film updateDate(Film film, LocalDate date);

    /**
     * Изменение времени фильма.
     */
    Film updateTime(Film film, LocalTime time);

    /**
     * Изменение стоимости фильма.
     */
    Film updateCost(Film film, Double cost);

    /**
     * Изменение фильма.
     */
    Film update(Film film, Long id);

    /**
     * Удаление фильма по ID из базы данных.
     */
    boolean deleteById(Long id);

}
