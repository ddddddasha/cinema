package service;

import model.Film;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FilmService {
    /**
     * Сохранение фильма в базу данных.
     */
    boolean create();

    /**
     * Получение фильма по логину.
     */
    Film getByFilmName(String filmName);

    /**
     * Получение фильма по ID.
     */
    Film getByFilmId(Long id);

    /**
     * Получение всех фильмов.
     */

    List<Film> getAllFilms();

    /**
     * Обновление фильма по ID.
     */

    boolean updateFilmById(Long id, Film film);

    /**
     * Удаление фильма по ID из базы данных.
     */
    boolean deleteFilmById(Long id);
    /**
     * Изменение названия фильма в билете.
     */
    boolean updateFilmName(Film film, String filmName);
    /**
     * Изменение даты фильма в билете.
     */
    boolean updateDate(Film film, LocalDate date);
    /**
     * Изменение времени начала фильма.
     */
    boolean updateTime(Film film, LocalTime time);
    /**
     * Изменение стоимости билета.
     */

    boolean updateCost(Film film, Double cost);

}
