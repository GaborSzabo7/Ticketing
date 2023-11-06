package hu.otpmobile.ticketing.api.infrastructure.integration.ticket.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaySeatRequest {

	final long eventId;
	final long seat;
	final String cardId;

}
