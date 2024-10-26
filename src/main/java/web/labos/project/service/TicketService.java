package web.labos.project.service;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import web.labos.project.dto.CreateTicketDTO;
import web.labos.project.model.Ticket;
import web.labos.project.repository.TicketRepository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    @Value("${app.url}")
    private String appUrl;

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public int getNumberOfTickets(){
        return ticketRepository.findAll().size();
    }

    public String createTicket(@Valid CreateTicketDTO ticketRequest) {
        Long vatin = ticketRequest.getVatin();

        if(ticketRequest.getFirstName() == null || ticketRequest.getLastName() == null || ticketRequest.getVatin() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ulazni JSON ne sadrži sve tražene podatke!");
        } else if(ticketRepository.countAllByVatin(vatin) > 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maksimalan broj ulaznica po OIB-u je 3!");
        }
        else{
            Ticket ticket = new Ticket();
            ticket.setVatin(vatin);
            ticket.setFirstName(ticketRequest.getFirstName());
            ticket.setLastName(ticketRequest.getLastName());
            ticket.setCreationTime(new Timestamp(System.currentTimeMillis()));
            Ticket createdTicket = ticketRepository.save(ticket);
            return appUrl + "/"+createdTicket.getId();
        }
    }

    public Optional<Ticket> findById(UUID id) {
       return ticketRepository.findById(id);
    }
}
