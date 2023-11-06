package hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatPriceRequest {

	private final long eventId;

	private final long seat;

}
