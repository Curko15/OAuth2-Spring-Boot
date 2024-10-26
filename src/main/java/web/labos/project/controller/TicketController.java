package web.labos.project.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import web.labos.project.dto.CreateTicketDTO;
import web.labos.project.model.Ticket;
import web.labos.project.service.TicketService;
import web.labos.project.util.QRCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("numberOfTickets", ticketService.getNumberOfTickets());
        return "landingPage";
    }

    @PostMapping("/createTicket")
    public ResponseEntity<?> createTicket(@RequestBody CreateTicketDTO ticketRequest) {
        try {
            String url = ticketService.createTicket(ticketRequest);
            if(url != null) {
                BufferedImage qrCodeImage = QRCode.generateQRCodeImage(url);


                ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
                ImageIO.write(qrCodeImage, "PNG", pngOutputStream);
                byte[] qrCodeBytes = pngOutputStream.toByteArray();


                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                return ResponseEntity.ok().headers(headers).body(qrCodeBytes);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public String ticketDetails(Model model, @PathVariable UUID id, @AuthenticationPrincipal OidcUser principal) {
        Optional<Ticket> ticket = ticketService.findById(id);
        model.addAttribute("name", principal.getAttribute("name"));
        ticket.ifPresent(value -> model.addAttribute("ticket", value));
        return "ticketDetails";
    }
}
