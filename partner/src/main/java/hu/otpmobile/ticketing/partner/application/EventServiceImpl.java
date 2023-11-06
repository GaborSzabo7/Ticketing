package hu.otpmobile.ticketing.partner.application;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.otpmobile.ticketing.partner.adapter.response.ReserveResponse;
import hu.otpmobile.ticketing.partner.infrastructure.exception.PartnerException;
import hu.otpmobile.ticketing.partner.infrastructure.file.EventDatasource;
import hu.otpmobile.ticketing.partner.model.EventData;
import hu.otpmobile.ticketing.partner.model.EventSeatData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

	private final EventDatasource eventDatasource;

	public EventServiceImpl(final EventDatasource eventDatasource) {
		requireNonNull(eventDatasource, "eventDatasource can't be null");
		this.eventDatasource = eventDatasource;
	}

	@Override
	public EventData getEvents() {
		log.debug("load events");
		return eventDatasource.loadEvents();
	}

	@Override
	public EventSeatData getEventSeats(final long eventId) {
		log.debug("load seat informations based on event id: {}", eventId);
		return eventDatasource.loadEventSeats(eventId);
	}

	@Override
	public ReserveResponse reserve(final long eventId, final long seat) {
		try {
			log.debug("try to reserve seat in {} event", eventId);
			reserveSeat(eventId, seat);
			return ReserveResponse.successResponse();
		} catch (PartnerException e) {
			return ReserveResponse.errorResponse(e.getErrorCode());
		}
	}

	@Override
	public int getSeatPrice(final long eventId, final long seatNumber) {
		log.debug("get price of given seat{}", seatNumber);

		final var seats = //
				Optional.ofNullable(eventDatasource.loadEventSeats(eventId)) //
						.map(EventSeatData::data) //
						.orElseThrow(() -> new PartnerException(90001));

		return seats.getPrice(seatNumber);
	}

	private void reserveSeat(final long eventId, final long seatNumber) {
		log.debug("choose seat number: {}", seatNumber);

		final var seats = //
				Optional.ofNullable(eventDatasource.loadEventSeats(eventId)) //
						.map(EventSeatData::data) //
						.orElseThrow(() -> new PartnerException(90001));

		seats.tryFindSeat(seatNumber) //
				.orElseThrow(() -> new PartnerException(90002));

		seats.tryReserveSeat(seatNumber) //
				.orElseThrow(() -> new PartnerException(90010));
	}

	@Override
	public long getEventStartTime(final long eventId) {
		log.debug("fetch start time of event: {}", eventId);
		return eventDatasource.loadEvents().getStartTimeOfEvent(eventId);
	}

}
