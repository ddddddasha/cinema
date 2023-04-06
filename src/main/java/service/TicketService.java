package service;

import model.Film;
import model.Ticket;
import java.util.List;

public interface TicketService {

    /**
     * ���������� ������� � ���� ������.
     */
    boolean createTickets(Film film);

    /**
     * ���������� ������ � ���� ������.
     */
    boolean buyTicket(Long userId);

    /**
     * ��������� ������ �� ID.
     */
    Ticket getByTicketId(Long id);

    /**
     * ��������� ���� ������� ������ ������������.
     */
    List<Ticket> getTicketsForUser(Long userId);

    /**
     * ���������� ������ �� ID.
     */
    boolean updateTicketById(Long id, Ticket ticket);

    /**
     * �������� ������ �� ID �� ���� ������.
     */
    boolean deleteTicketById(Long ticketId, Long userId);

    /**
     * �������� ��������� ���� �� �����.
     */
    void  getFreeTickets(Long filmId);

    /**
     * �������� ������� �� ID ������.
     */
    boolean deleteTicketsByFilmId(Long filmId);
}
