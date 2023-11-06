package hu.otpmobile.ticketing.ticket.adapter;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.otpmobile.ticketing.ticket.adapter.response.PaySeatResponse;
import hu.otpmobile.ticketing.ticket.application.TicketService;
import hu.otpmobile.ticketing.ticket.infrastructure.adapter.BaseRestController;
import hu.otpmobile.ticketing.ticket.model.EventData;
import hu.otpmobile.ticketing.ticket.model.EventSeatData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class TicketRestController extends BaseRestController {

	private final TicketService ticketService;

	public TicketRestController(final TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@Operation(summary = "Gives back all the event what partner has")
	@GetMapping("/event/all")
	public Callable<ResponseEntity<EventData>> getEvents() {
		return get(() -> ticketService.getPartnerEvents());
	}

	@Operation(summary = "Gives back all seats of the given event id")
	@GetMapping("/event/seats/{eventId}")
	public Callable<ResponseEntity<EventSeatData>> getEvents(@PathVariable final long eventId) {
		return get(() -> ticketService.getEventSeats(eventId));
	}

	@Operation(summary = "User can pay the price of seat with given card")
	@PostMapping("/pay/{eventId}/{seat}/{cardId}")
	public Callable<ResponseEntity<PaySeatResponse>> paySeat( //
			@PathVariable final long eventId, //
			@PathVariable final long seat, //
			@PathVariable final String cardId) {
		return post(() -> ticketService.paySeat(eventId, seat, cardId));
	}

}
