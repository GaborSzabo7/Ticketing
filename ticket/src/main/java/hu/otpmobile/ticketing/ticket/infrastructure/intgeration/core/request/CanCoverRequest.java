package hu.otpmobile.ticketing.ticket.infrastructure.intgeration.core.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CanCoverRequest {

	private final String cardId;
	private final int price;

}
