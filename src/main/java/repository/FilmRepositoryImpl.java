package repository;

import util.dateTime.DateTimeValidator;
import util.dateTime.DateTimeValidatorImpl;
import util.ConnectionManager;
import model.Film;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FilmRepositoryImpl implements FilmRepository{
    private final DateTimeValidator validator = new DateTimeValidatorImpl();
    private final TicketRepository ticketRepository = new TicketRepositoryImpl();

    @Override
    public boolean create(Film film) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO film (filmName, filmDate, filmTime, filmCost) VALUES (?,?,?,?)");//filmId - автоинкремент переделать
            statement.setString(1, film.getFilmName());
            statement.setDate(2, validator.convertToDate(film.getFilmDate()));
            statement.setTime(3, validator.convertToTime(film.getFilmTime()));
            statement.setDouble(4, film.getCost());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Film getById(Long id) {
        Film film = new Film();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT filmId, filmName, filmDate, filmTime, filmCost FROM film WHERE filmId=?");//инъекции - ?
            statement.setLong(1, id);//почитать про индексы
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String filmName = resultSet.getString("filmName");///почитать как работает это
                Date filmDate = resultSet.getDate("filmDate");
                Time filmTime = resultSet.getTime("filmTime");
                Double filmCost = resultSet.getDouble("filmCost");

                film.setFilmId(id);
                film.setFilmName(filmName);
                film.setFilmDate(validator.convertToLocalDate(filmDate));
                film.setFilmTime(validator.convertToLocalTime(filmTime));
                film.setCost(filmCost);
                return film;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Film getByFilmName(String filmName) {
        Film film = new Film();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT filmId, filmDate, filmTime, filmCost FROM film WHERE filmName=?");//инъекции - ?
            statement.setString(1, filmName);//почитать про индексы
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long filmId = resultSet.getLong("filmId");
                Date filmDate = resultSet.getDate("filmDate");
                Time filmTime = resultSet.getTime("filmTime");
                Double filmCost = resultSet.getDouble("filmCost");
                film.setFilmId(filmId);
                film.setFilmName(filmName);
                film.setFilmDate(validator.convertToLocalDate(filmDate));
                film.setFilmTime(validator.convertToLocalTime(filmTime));
                film.setCost(filmCost);
                return film;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;//переделать на нету таких пользователей
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM film"); //соединение с бд
            ResultSet resultSet = statement.executeQuery();//извлекаем объект типа ResultSet из бд
            while (resultSet.next()) {
                Long filmId = resultSet.getLong("filmId");
                String filmName = resultSet.getString("filmName");
                Date filmDate = resultSet.getDate("filmDate");
                Time filmTime = resultSet.getTime("filmTime");
                Double filmCost = resultSet.getDouble("filmCost");
                Film film = new Film();//либо через конструктор
                film.setFilmId(filmId);
                film.setFilmName(filmName);
                film.setFilmDate(validator.convertToLocalDate(filmDate));
                film.setFilmTime(validator.convertToLocalTime(filmTime));
                film.setCost(filmCost);
                films.add(film);
            }
            return films;
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException(e);
        }
    }

    @Override
    public Film updateFilmName(Film film, String filmName) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE film SET filmName=? WHERE filmId=?");
            statement.setString(1, filmName);
            statement.setLong(2, film.getFilmId());
            statement.executeUpdate();
            ticketRepository.updateFilmName(film, filmName);
            return film;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public Film updateDate(Film film, LocalDate date) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE film SET filmDate=? WHERE filmId=?");
            statement.setDate(1, validator.convertToDate(date));
            statement.setLong(2, film.getFilmId());
            statement.executeUpdate();
            ticketRepository.updateFilmDate(film, date);
            return film;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public Film updateTime(Film film, LocalTime time) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE film SET filmTime=? WHERE filmId=?");
            statement.setTime(1, validator.convertToTime(time));
            statement.setLong(2, film.getFilmId());
            statement.executeUpdate();
            ticketRepository.updateFilmTime(film, time);
            return film;
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public Film updateCost(Film film, Double cost) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE film SET filmCost=? WHERE filmId=?");
            statement.setDouble(1, cost);
            statement.setLong(2, film.getFilmId());
            statement.executeUpdate();
            ticketRepository.updateFilmCost(film, cost);
            return film;
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public Film update(Film film, Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE film SET filmName=?, filmDate=?, filmTime=? WHERE filmId=?");
            statement.setString(1, film.getFilmName());
            statement.setDate(2, validator.convertToDate(film.getFilmDate()));
            statement.setTime(3, validator.convertToTime(film.getFilmTime()));
            statement.setLong(4, id);
            statement.executeUpdate();
            return film;//возвращает в таком случае false –ј«ќЅ–ј“№

        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM film WHERE filmId=?");
            statement.setLong(1, id);
            return statement.execute();//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }
}
