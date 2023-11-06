package hu.otpmobile.ticketing.partner.model;

import java.util.List;

import hu.otpmobile.ticketing.partner.infrastructure.exception.PartnerException;

public record EventData(List<Event> data, boolean success) {

	public Event getEventBy(long eventId) {
		return data.stream() //
				.filter(e -> e.eventId() == eventId) //
				.findFirst() //
				.orElseThrow(() -> new PartnerException(90001));
	}

	public long getStartTimeOfEvent(long eventId) {
		return data.stream() //
				.filter(e -> e.eventId() == eventId) //
				.findFirst() //
				.map(Event::startTimeStamp) //
				.orElseThrow(() -> new PartnerException(90001));
	}

}
