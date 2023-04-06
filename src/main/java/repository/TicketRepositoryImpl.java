package repository;

import model.Film;
import model.Ticket;
import util.dateTime.DateTimeValidator;
import util.dateTime.DateTimeValidatorImpl;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository{
    private final DateTimeValidator validator = new DateTimeValidatorImpl();

    @Override
    public boolean create(Ticket ticket, Film film) {
        FilmRepository filmRepository = new FilmRepositoryImpl();
        Long filmId = filmRepository.getByFilmName(ticket.getFilmName()).getFilmId();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO ticket (userId, filmName, filmId, rowNumber, colNumber, cost, isPurchased) VALUES (?,?,?,?,?,?,?)");//ticketId - автоинкремент переделать
            statement.setNull(1, 0);
            statement.setString(2, ticket.getFilmName());
            statement.setLong(3, filmId);
            statement.setLong(4, ticket.getRowNumber());
            statement.setLong(5, ticket.getColNumber());
            statement.setDouble(6, film.getCost());//переместить в стоимость фильма задавать стоимость
            statement.setBoolean(7, false);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean buyTicket(Ticket ticket) {
        FilmRepository filmRepository = new FilmRepositoryImpl();//перенести переменные класса
        Long filmId = filmRepository.getByFilmName(ticket.getFilmName()).getFilmId();//возможно перенести в сервис
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE ticket SET userId=?, isPurchased=? WHERE filmId=? AND rowNumber=? AND colNumber=?");
            statement.setLong(1, ticket.getUserId());
            statement.setBoolean(2, true);
            statement.setLong(3, filmId);
            statement.setLong(4, ticket.getRowNumber());
            statement.setLong(5, ticket.getColNumber());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Ticket getTicketById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT ticketId, userId, filmName, filmId, rowNumber, colNumber, cost, isPurchased FROM ticket WHERE ticketId=?");//инъекции - ?
            statement.setLong(1, id);//почитать про индексы
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Long filmId = resultSet.getLong("filmId");
                Long userId = resultSet.getLong("userId");
                String filmName = resultSet.getString("filmName");
                Long filmId = resultSet.getLong("filmId");
                Long rowNumber = resultSet.getLong("rowNumber");
                Long colNumber = resultSet.getLong("colNumber");
                Double cost = resultSet.getDouble("cost");///почитать как работает это
                boolean isPurchased = resultSet.getBoolean("isPurchased");
                return new Ticket(id, userId, filmName, filmId, rowNumber, colNumber, cost, isPurchased);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;//переделать на нету таких пользователей
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket"); //соединение с бд
            ResultSet resultSet = statement.executeQuery();//извлекаем объект типа ResultSet из бд
            while (resultSet.next()) {
                Long ticketId = resultSet.getLong("ticketId");
                Long userId = resultSet.getLong("userId");
                String filmName = resultSet.getString("filmName");
                Long filmId = resultSet.getLong("filmId");
                Long rowNumber = resultSet.getLong("rowNumber");
                Long colNumber = resultSet.getLong("colNumber");
                Double cost = resultSet.getDouble("cost");///почитать как работает это
                boolean isPurchased = resultSet.getBoolean("isPurchased");
                Ticket ticket = new Ticket(ticketId, userId, filmName, filmId, rowNumber, colNumber, cost, isPurchased);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> getTicketsForUser(Long userId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket WHERE userId=?"); //соединение с бд
            statement.setLong(1, userId);//почитать про индексы
            ResultSet resultSet = statement.executeQuery();//извлекаем объект типа ResultSet из бд
            while (resultSet.next()) {
                Long ticketId = resultSet.getLong("ticketId");
                String filmName = resultSet.getString("filmName");
                Long filmId = resultSet.getLong("filmId");
                Long rowNumber = resultSet.getLong("rowNumber");
                Long colNumber = resultSet.getLong("colNumber");
                Double cost = resultSet.getDouble("cost");
                boolean isPurchased = resultSet.getBoolean("isPurchased");
                Ticket ticket = new Ticket(ticketId, userId, filmName, filmId, rowNumber, colNumber, cost, isPurchased);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket update(Ticket ticket, Long id) { //нужно или нет потом удалить
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET userId=?, filmName=?, filmId=?, rowNumber=?, colNumber=?, cost=?, isPurchased=? WHERE ticketId=?");
            statement.setLong(1, ticket.getUserId());
            statement.setString(2, ticket.getFilmName());
            statement.setLong(3, ticket.getFilmId());
            statement.setLong(4, ticket.getRowNumber());
            statement.setLong(5, ticket.getColNumber());
            statement.setDouble(6, ticket.getCost());
            statement.setBoolean(7, ticket.getIsPurchased());
            statement.setLong(8, id);
            statement.executeUpdate();
            return ticket;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(Long ticketId, Long userId) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ticket WHERE ticketId=? AND userId=?");
            statement.setLong(1, ticketId);
            statement.setLong(2, userId);
            return statement.execute();//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }

    @Override
    public List<Ticket> getAllTicketByFilmId(Long filmId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket WHERE filmId=?"); //соединение с бд
            statement.setLong(1, filmId);//почитать про индексы
            ResultSet resultSet = statement.executeQuery();//извлекаем объект типа ResultSet из бд
            while (resultSet.next()) {
                String filmName = resultSet.getString("filmName");
                Long rowNumber = resultSet.getLong("rowNumber");
                Long colNumber = resultSet.getLong("colNumber");
                boolean isPurchased = resultSet.getBoolean("isPurchased");
                Ticket ticket = new Ticket();
                ticket.setFilmId(filmId);
                ticket.setFilmName(filmName);
                ticket.setRowNumber(rowNumber);
                ticket.setColNumber(colNumber);
                ticket.setPurchased(isPurchased);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateFilmName(Film film, String filmName) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET filmName=? WHERE filmId=?");
            statement.setString(1, filmName);
            statement.setLong(2, film.getFilmId());

            statement.executeUpdate();
            return true;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            return false;

        }
    }

    @Override
    public boolean updateFilmDate(Film film, LocalDate date) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET filmDate=? WHERE filmId=?");
            statement.setDate(1, validator.convertToDate(date));
            statement.setLong(2, film.getFilmId());

            statement.executeUpdate();
            return true;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            return false;

        }
    }

    @Override
    public boolean updateFilmTime(Film film, LocalTime time) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET filmTime=? WHERE filmId=?");
            statement.setTime(1, validator.convertToTime(time));
            statement.setLong(2, film.getFilmId());

            statement.executeUpdate();
            return true;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            return false;

        }
    }

    @Override
    public boolean updateFilmCost(Film film, Double cost) { //добавить цикл или hasNext
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET cost=? WHERE filmId=?");
            statement.setDouble(1, cost);
            statement.setLong(2, film.getFilmId());

            statement.executeUpdate();
            return true;//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            return false;

        }
    }

    @Override
    public boolean deleteTicketsByFilmId(Long filmId) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ticket WHERE filmId=?");
            statement.setLong(1, filmId);
            return statement.execute();//возвращает в таком случае false –ј«ќЅ–ј“№
        } catch (SQLException e) {
            //логирование
            throw new RuntimeException();
        }
    }
}
