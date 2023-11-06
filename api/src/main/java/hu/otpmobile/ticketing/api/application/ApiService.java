package hu.otpmobile.ticketing.api.application;

import hu.otpmobile.ticketing.api.adapter.response.EventData;
import hu.otpmobile.ticketing.api.adapter.response.EventSeatData;
import hu.otpmobile.ticketing.api.adapter.response.PaySeatResponse;

public interface ApiService {

	EventData getEvents(String token);

	EventSeatData getEventSeats(String token, long eventId);

	PaySeatResponse paySeat(String token, long eventId, long seat, String cardId);

}
