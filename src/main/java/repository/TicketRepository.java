package repository;

import model.Film;
import model.Ticket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TicketRepository {
    /**
     * �������� ������ � ��������� � ���� ������.
     */
    boolean create(Ticket ticket, Film film);
    /**
     * ������� ������.
     */
    boolean buyTicket(Ticket ticket);
    /**
     * ��������� ������ �� id.
     */
    Ticket getTicketById(Long id);
    /**
     * ��������� ���� ������� ������������� ������������.
     */
    List<Ticket> getAllTickets();
    /**
     * ��������� ���� ������� ������������� ������������.
     */
    List<Ticket> getTicketsForUser(Long userId);
    /**
     * ��������� ������ .
     */
    Ticket update(Ticket ticket, Long id);
    /**
     * �������� ������ ������������� ������������ �� ��� ID.
     */
    boolean deleteById(Long ticketId, Long userId);
    /**
     * ��������� ���� ������� ������������� ������.
     */
    List<Ticket> getAllTicketByFilmId(Long ticketId);
    /**
     * ��������� �������� ������.
     */
    boolean updateFilmName(Film film, String filmName);
    /**
     * ��������� ���� ������.
     */
    boolean updateFilmDate(Film film, LocalDate date);
    /**
     * ��������� ������� ������.
     */
    boolean updateFilmTime(Film film, LocalTime time);
    /**
     * ��������� ��������� ������.
     */
    boolean updateFilmCost(Film film, Double cost);
    /**
     * �������� ������� ������������� ������ �� ���� ������.
     */
    boolean deleteTicketsByFilmId(Long filmId);
}
