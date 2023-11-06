package hu.otpmobile.ticketing.ticket.application;

import org.springframework.stereotype.Service;

import hu.otpmobile.ticketing.ticket.adapter.response.PaySeatResponse;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.core.CoreService;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.core.request.CanCoverRequest;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.PartnerService;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.request.ReserveSeatRequest;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.request.SeatPriceRequest;
import hu.otpmobile.ticketing.ticket.model.EventData;
import hu.otpmobile.ticketing.ticket.model.EventSeatData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

	private final CoreService coreService;
	private final PartnerService partnerService;

	public TicketServiceImpl(final CoreService coreService, final PartnerService partnerService) {
		this.coreService = coreService;
		this.partnerService = partnerService;
	}

	@Override
	public EventData getPartnerEvents() {
		return partnerService.getEvents();
	}

	@Override
	public EventSeatData getEventSeats(long eventId) {
		return partnerService.getEventSeats(eventId);
	}

	@Override
	public PaySeatResponse paySeat(final long eventId, final long seat, final String cardId) {
		log.debug("try to pay price for seat: {}", seat);

		final var eventStartTime = partnerService.getEventStartTime(eventId);

		if (eventStartTime < System.currentTimeMillis()) {
			log.debug("Too late to pay event has already started.");
			return PaySeatResponse.buildFailedResponse(20011);
		}

		log.debug("try to reserve seat for event {}", eventId);
		final var reserveRespone = partnerService.reserveSeat(new ReserveSeatRequest(eventId, seat));

		if (!reserveRespone.isSuccess()) {
			if (reserveRespone.getCode() == 90001) {
				log.debug("No such event: {}", eventId);
				return PaySeatResponse.buildFailedResponse(20001);
			} else if (reserveRespone.getCode() == 90002) {
				log.debug("No such seat: {}", seat);
				return PaySeatResponse.buildFailedResponse(20002);
			} else if (reserveRespone.getCode() == 90002) {
				log.debug("Seat is already reserved : {}", seat);
				return PaySeatResponse.buildFailedResponse(90010);
			}
		}

		final var price = partnerService.getSeatPrice(new SeatPriceRequest(eventId, seat));
		final var canCoverResponse = coreService.canCover(new CanCoverRequest(cardId, price));

		if (canCoverResponse == false) {
			log.debug("There is no enough money in the account to cover price.");
			return PaySeatResponse.buildFailedResponse(20012);
		}

		return PaySeatResponse.buildSuccessResponse();
	}

}
