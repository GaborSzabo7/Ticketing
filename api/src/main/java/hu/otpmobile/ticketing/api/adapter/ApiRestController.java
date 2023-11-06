package hu.otpmobile.ticketing.api.adapter;

import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.otpmobile.ticketing.api.adapter.response.EventData;
import hu.otpmobile.ticketing.api.adapter.response.EventSeatData;
import hu.otpmobile.ticketing.api.adapter.response.PaySeatResponse;
import hu.otpmobile.ticketing.api.application.ApiService;
import hu.otpmobile.ticketing.api.infrastructure.adapter.BaseRestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class ApiRestController extends BaseRestController {

	private final ApiService apiService;

	public ApiRestController(ApiService apiService) {
		this.apiService = apiService;
	}

	@Operation(summary = "Gives back all the event what partner has")
	@GetMapping("/event/all")
	public Callable<ResponseEntity<EventData>> getEvents(@RequestHeader("User-Token") final String token) {
		return get(() -> apiService.getEvents(token));
	}

	@Operation(summary = "Gives back all seats of the given event id")
	@GetMapping("/event/seats/{eventId}")
	public Callable<ResponseEntity<EventSeatData>> getEvents( //
			@RequestHeader("User-Token") final String token, //
			@PathVariable final long eventId) {
		return get(() -> apiService.getEventSeats(token, eventId));
	}

	@Operation(summary = "User can pay the price of seat with given card")
	@PostMapping("/pay/{eventId}/{seat}/{cardId}")
	public Callable<ResponseEntity<PaySeatResponse>> paySeat( //
			@RequestHeader("User-Token") final String token, //
			@PathVariable final long eventId, //
			@PathVariable final long seat, //
			@PathVariable final String cardId) {
		return post(() -> apiService.paySeat(token, eventId, seat, cardId));
	}

}
