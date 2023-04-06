package service;

import model.Film;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FilmService {
    /**
     * ���������� ������ � ���� ������.
     */
    boolean create();

    /**
     * ��������� ������ �� ������.
     */
    Film getByFilmName(String filmName);

    /**
     * ��������� ������ �� ID.
     */
    Film getByFilmId(Long id);

    /**
     * ��������� ���� �������.
     */

    List<Film> getAllFilms();

    /**
     * ���������� ������ �� ID.
     */

    boolean updateFilmById(Long id, Film film);

    /**
     * �������� ������ �� ID �� ���� ������.
     */
    boolean deleteFilmById(Long id);
    /**
     * ��������� �������� ������ � ������.
     */
    boolean updateFilmName(Film film, String filmName);
    /**
     * ��������� ���� ������ � ������.
     */
    boolean updateDate(Film film, LocalDate date);
    /**
     * ��������� ������� ������ ������.
     */
    boolean updateTime(Film film, LocalTime time);
    /**
     * ��������� ��������� ������.
     */

    boolean updateCost(Film film, Double cost);

}
