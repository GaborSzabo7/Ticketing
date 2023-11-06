package hu.otpmobile.ticketing.partner.application;

import hu.otpmobile.ticketing.partner.adapter.response.ReserveResponse;
import hu.otpmobile.ticketing.partner.model.EventData;
import hu.otpmobile.ticketing.partner.model.EventSeatData;

public interface EventService {

	EventData getEvents();

	EventSeatData getEventSeats(long eventId);

	ReserveResponse reserve(long eventId, long seat);

	int getSeatPrice(long eventId, long seatNumber);

	long getEventStartTime(long eventId);

}
