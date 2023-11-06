package hu.otpmobile.ticketing.partner.infrastructure.file;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.otpmobile.ticketing.partner.model.EventData;
import hu.otpmobile.ticketing.partner.model.EventSeatData;

@Component
public class FileDatasource implements EventDatasource {

	private static final String EVENTS = "/getEvents.json";
	private static final String EVENT_1 = "/getEvent1.json";
	private static final String EVENT_2 = "/getEvent2.json";
	private static final String EVENT_3 = "/getEvent3.json";

	private final ObjectMapper objectMapper;

	public FileDatasource(final ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public EventData loadEvents() {
		return load(EventData.class, EVENTS);
	}

	@Override
	public EventSeatData loadEventSeats(final long eventId) {
		return switch ((int) eventId) {
		case 1 -> load(EventSeatData.class, EVENT_1);
		case 2 -> load(EventSeatData.class, EVENT_2);
		case 3 -> load(EventSeatData.class, EVENT_3);
		default -> null;
		};
	}

	private <T> T load(final Class<T> c, final String filename) {
		try {
			return objectMapper.readValue(FileDatasource.class.getResource(filename), c);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
