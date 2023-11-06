package hu.otpmobile.ticketing.ticket.application;

import hu.otpmobile.ticketing.ticket.adapter.response.PaySeatResponse;
import hu.otpmobile.ticketing.ticket.model.EventData;
import hu.otpmobile.ticketing.ticket.model.EventSeatData;

public interface TicketService {

	EventData getPartnerEvents();

	EventSeatData getEventSeats(long eventId);

	PaySeatResponse paySeat(long eventId, long seat, String cardId);

}
