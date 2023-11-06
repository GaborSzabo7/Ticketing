package hu.otpmobile.ticketing.partner.infrastructure.file;

import hu.otpmobile.ticketing.partner.model.EventData;
import hu.otpmobile.ticketing.partner.model.EventSeatData;

public interface EventDatasource {

	EventData loadEvents();

	EventSeatData loadEventSeats(long eventId);

}
