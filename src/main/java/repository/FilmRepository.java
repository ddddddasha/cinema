package repository;

import model.Film;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FilmRepository {

    /**
     * �������� � ���������� ������ � ���� ������.
     */
    boolean create(Film film);

    /**
     * ��������� ������ �� ID.
     */
    Film getById(Long id);

    /**
     * ��������� ������ �� ��������.
     */
    Film getByFilmName(String username);

    /**
     * ��������� ���� �������.
     */
    List<Film> getAllFilms();

    /**
     * ��������� ������ �� ��������.
     */
    Film updateFilmName(Film film, String filmName);

    /**
     * ��������� ���� ������.
     */
    Film updateDate(Film film, LocalDate date);

    /**
     * ��������� ������� ������.
     */
    Film updateTime(Film film, LocalTime time);

    /**
     * ��������� ��������� ������.
     */
    Film updateCost(Film film, Double cost);

    /**
     * ��������� ������.
     */
    Film update(Film film, Long id);

    /**
     * �������� ������ �� ID �� ���� ������.
     */
    boolean deleteById(Long id);

}
