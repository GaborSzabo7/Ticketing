package hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReserveSeatRequest {

	private final long eventId;

	private final long seat;

}
