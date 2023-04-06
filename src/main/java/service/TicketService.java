package service;

import model.Film;
import model.Ticket;
import java.util.List;

public interface TicketService {

    /**
     * Добавление билетов в базу данных.
     */
    boolean createTickets(Film film);

    /**
     * Сохранение билета в базу данных.
     */
    boolean buyTicket(Long userId);

    /**
     * Получение билета по ID.
     */
    Ticket getByTicketId(Long id);

    /**
     * Получение всех билетов одного пользователя.
     */
    List<Ticket> getTicketsForUser(Long userId);

    /**
     * Обновление билета по ID.
     */
    boolean updateTicketById(Long id, Ticket ticket);

    /**
     * Удаление билета по ID из базы данных.
     */
    boolean deleteTicketById(Long ticketId, Long userId);

    /**
     * Просмотр свободных мест на фильм.
     */
    void  getFreeTickets(Long filmId);

    /**
     * Удаление билетов по ID фильма.
     */
    boolean deleteTicketsByFilmId(Long filmId);
}
