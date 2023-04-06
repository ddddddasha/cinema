package repository;

import model.Film;
import model.Ticket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TicketRepository {
    /**
     * Создание билета и помещение в базу данных.
     */
    boolean create(Ticket ticket, Film film);
    /**
     * Покупка билета.
     */
    boolean buyTicket(Ticket ticket);
    /**
     * Получение билета по id.
     */
    Ticket getTicketById(Long id);
    /**
     * Получение всех билетов определенного пользователя.
     */
    List<Ticket> getAllTickets();
    /**
     * Получение всех билетов определенного пользователя.
     */
    List<Ticket> getTicketsForUser(Long userId);
    /**
     * Изменение билета .
     */
    Ticket update(Ticket ticket, Long id);
    /**
     * Удаление билета определенного пользователя по его ID.
     */
    boolean deleteById(Long ticketId, Long userId);
    /**
     * Получение всех билетов определенного фильма.
     */
    List<Ticket> getAllTicketByFilmId(Long ticketId);
    /**
     * Изменение названия фильма.
     */
    boolean updateFilmName(Film film, String filmName);
    /**
     * Изменение даты фильма.
     */
    boolean updateFilmDate(Film film, LocalDate date);
    /**
     * Изменение времени фильма.
     */
    boolean updateFilmTime(Film film, LocalTime time);
    /**
     * Изменение стоимости фильма.
     */
    boolean updateFilmCost(Film film, Double cost);
    /**
     * Удаление билетов определенного фильма из базы данных.
     */
    boolean deleteTicketsByFilmId(Long filmId);
}
