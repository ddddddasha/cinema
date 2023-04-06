package service;

import model.Film;
import model.Ticket;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;
import java.util.List;
import java.util.Scanner;
import static util.constants.Constants.*;

public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository = new TicketRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean createTickets(Film film) {
        Ticket ticket = new Ticket();
        ticket.setFilmId(film.getFilmId());
        ticket.setFilmName(film.getFilmName());
        for (long i = 0; i < 6; i++) {
            for (long j = 0; j < 8; j++) {
                ticket.setRowNumber(i);
                ticket.setColNumber(j);
                ticketRepository.create(ticket, film);
            }
        }
        return false;
    }

    @Override
    public boolean buyTicket(Long userId) {
        Ticket ticket = new Ticket();
        System.out.println(ENTER_FILM_NAME_TO_CREATE);
        String filmName = scanner.nextLine();
        System.out.println(CHOOSE_SEAT);
        System.out.println(ROW_NUMBER);
        Long rowNumber = scanner.nextLong();
        System.out.println(COL_NUMBER);
        Long colNumber = scanner.nextLong();
        System.out.println(ENTER_TICKET_COST);
        Double cost = scanner.nextDouble();
        ticket.setUserId(userId);
        ticket.setFilmName(filmName);
        ticket.setRowNumber(rowNumber);
        ticket.setColNumber(colNumber);
        ticket.setCost(cost);
        ticket.setPurchased(true);
        return ticketRepository.buyTicket(ticket);
    }

    @Override
    public Ticket getByTicketId(Long id) {
        return ticketRepository.getTicketById(id);
    }

    @Override
    public List<Ticket> getTicketsForUser(Long userId) {
        List<Ticket> tickets = ticketRepository.getTicketsForUser(userId);
        for (Ticket item: tickets) {
            System.out.println(item);
        }
        return ticketRepository.getTicketsForUser(userId);
    }

    @Override
    public boolean updateTicketById(Long id, Ticket ticket) {
        ticketRepository.update(ticket, id);
        return true;
    }

    @Override
    public boolean deleteTicketById(Long ticketId, Long userId) {
        return ticketRepository.deleteById(ticketId, userId);
    }

    public void getFreeTickets(Long filmId){
        List<Ticket> tickets = ticketRepository.getAllTicketByFilmId(filmId);
        int count = 0;
        int i = 0;
        System.out.println(TABLE_ROW);
        System.out.print(TABLE_LINE);
        for (Ticket item : tickets) {
            if(count % EIGHT == ZERO_INT ) {
                System.out.println();
                System.out.print(i + TABLE_COL);
                i++;
            }
            if(!item.getIsPurchased()){
                System.out.print("  " + ANSI_GREEN + SEAT_STATUS_FREE + ANSI_RESET + "   ");
            }
            else{
                System.out.print("  " + ANSI_RED + SEAT_STATUS_NONE + ANSI_RESET + "   " );

            }
            count++;
        }
        System.out.println();
    }

    @Override
    public boolean deleteTicketsByFilmId(Long filmId) {
        for (long i = 0; i < SIX_INT; i++) {
            for (long j = 0; j < EIGHT; j++) {
                ticketRepository.deleteTicketsByFilmId(filmId);
            }
        }
        return true;
    }
}
