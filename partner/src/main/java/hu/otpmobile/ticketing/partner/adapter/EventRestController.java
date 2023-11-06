package hu.otpmobile.ticketing.partner.adapter;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.otpmobile.ticketing.partner.adapter.response.ReserveResponse;
import hu.otpmobile.ticketing.partner.application.EventService;
import hu.otpmobile.ticketing.partner.infrastructure.adapter.BaseRestController;
import hu.otpmobile.ticketing.partner.model.EventData;
import hu.otpmobile.ticketing.partner.model.EventSeatData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/event")
public class EventRestController extends BaseRestController {

	private final EventService eventService;

	public EventRestController(final EventService eventService) {
		requireNonNull(eventService, "eventService can't be null");
		this.eventService = eventService;
	}

	@Operation(summary = "Gives back all the event what partner has")
	@GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<EventData>> getEvents() {
		return get(() -> eventService.getEvents());
	}

	@Operation(summary = "Gives back all seats of the given event id")
	@GetMapping(path = "/seats/{eventId}", produces = APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<EventSeatData>> getEvents(@PathVariable final long eventId) {
		return get(() -> eventService.getEventSeats(eventId));
	}

	@Operation(summary = "Reserve seat for given event")
	@PostMapping(path = "/reserve/{eventId}/{seat}", produces = APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<ReserveResponse>> reserve(@PathVariable final long eventId, @PathVariable final long seat) {
		return post(() -> eventService.reserve(eventId, seat));
	}

	@Operation(summary = "Get back price of the seat of event")
	@PostMapping(path = "/price/{eventId}/{seat}", produces = APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<Integer>> getSeatPrice(@PathVariable final long eventId, @PathVariable final long seat) {
		return post(() -> eventService.getSeatPrice(eventId, seat));
	}

	@Operation(summary = "Get start time of an event")
	@PostMapping(path = "/starttime/{eventId}", produces = APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<Long>> getStarTime(@PathVariable final long eventId) {
		return post(() -> eventService.getEventStartTime(eventId));
	}

}
